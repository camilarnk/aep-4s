console.log("🌳 OcupaMais publicacoes.js carregado!");

// Função para carregar publicações do backend 
async function carregarPublicacoes() {
  const postList = document.getElementById("postList");
  if (!postList) return;

  try {
    const resposta = await fetch("http://localhost:8080/publicacoes");
    if (!resposta.ok) throw new Error("Erro ao buscar publicações");

    const publicacoes = await resposta.json();

    // Mantém o feed fake e adiciona as publicações reais abaixo
    postList.insertAdjacentHTML("beforeend", gerarHTMLPublicacoes(publicacoes));
  } catch (erro) {
    console.error("Erro ao carregar publicações:", erro);
  }
}

// Gera HTML para as publicações vindas do backend
function gerarHTMLPublicacoes(lista) {
  return lista.map(pub => `
    <article class="post" data-id="${pub.id}">
      <div class="post-header">
        <div class="avatar">${iniciais(pub.nomeUsuario)}</div>
        <div class="post-meta">
          <span class="author">${pub.nomeUsuario}</span>
          <span class="info">${pub.nomeEspaco}</span>
        </div>
        <span class="status-badge status-pendente">Pendente</span>
      </div>
      <img class="post-image" src="" alt="Publicação">
      <div class="post-actions">
        <button class="like-btn" type="button" onclick="curtirPublicacao(${pub.id})">❤️ Curtir</button>
        <span class="likes-count" id="likes-${pub.id}" data-count="0">0 curtidas</span>
      </div>
      <p class="caption">${pub.descricao}</p>
    </article>
  `).join("");
}

function iniciais(nome) {
  if (!nome || typeof nome !== "string") return "?";
  const partes = nome.trim().split(" ");
  return partes.map(p => p[0]).join("").slice(0, 2).toUpperCase();
}

// Criar nova publicação
document.getElementById("btnPostar")?.addEventListener("click", async () => {
  const descricao = document.getElementById("postText").value.trim();
  const nomeEspaco = document.getElementById("postLocal").value.trim();
  const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));

  if (!descricao || !usuario) {
    return alert("Faça login e preencha o campo.");
  }

  const novaPublicacao = {
    usuarioId: usuario.id,
    nomeEspaco: nomeEspaco,
    descricao: descricao
  };

  console.log("📤 Enviando nova publicação:", novaPublicacao); // para debug

  try {
    if (!descricao || !usuario || !nomeEspaco) {
      return alert("Preencha o texto e o local antes de publicar.");
    }
    
    const resposta = await fetch("http://localhost:8080/publicacoes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(novaPublicacao)
    });

    if (!resposta.ok) throw new Error("Erro ao publicar");
    const pubCriada = await resposta.json();

    alert("✅ Publicação criada!");
    document.getElementById("postText").value = "";

    // Insere no topo
    document.getElementById("postList").insertAdjacentHTML("afterbegin", gerarHTMLPublicacoes([pubCriada]));
  } catch (erro) {
    console.error("Erro ao criar publicação:", erro);
    alert("Erro ao criar publicação.");
  }
});

// Animação e contagem do botão Curtir
document.addEventListener("click", (e) => {
  if (e.target.classList.contains("like-btn")) {
    const btn = e.target;
    const countSpan = btn.nextElementSibling;
    let count = parseInt(countSpan.dataset.count, 10);

    btn.classList.toggle("liked");
    if (btn.classList.contains("liked")) {
      count++;
      btn.textContent = "💚 Curtido";
    } else {
      count--;
      btn.textContent = "❤️ Curtir";
    }
    countSpan.dataset.count = count;
    countSpan.textContent = `${count} curtidas`;
  }
});

document.addEventListener("DOMContentLoaded", carregarPublicacoes);
console.log("🌳 OcupaMais publicacoes.js carregado!");

// Função para carregar publicações do backend 
async function carregarPublicacoes() {
  const postList = document.getElementById("postList");
  if (!postList) return;

  try {
    const resposta = await fetch("http://localhost:8080/publicacoes");
    if (!resposta.ok) throw new Error("Erro ao buscar publicações");

    let publicacoes = await resposta.json();
    publicacoes.sort((a, b) => new Date(b.dataCriacao) - new Date(a.dataCriacao));
    
    // Insere publicações no topo
    postList.insertAdjacentHTML("afterbegin", gerarHTMLPublicacoes(publicacoes));
  } catch (erro) {
    console.error("Erro ao carregar publicações:", erro);
  }
}

// Gera HTML para as publicações vindas do backend
function gerarHTMLPublicacoes(lista) {
  return lista.map(pub => {
    const data = pub.dataCriacao
      ? new Date(pub.dataCriacao).toLocaleString("pt-BR", {
          day: "2-digit", month: "2-digit", year: "numeric",
          hour: "2-digit", minute: "2-digit"
        })
      : "Data não disponível";

    const statusClass = pub.status
      ? `status-${pub.status.toLowerCase().replace("_", "-")}`
      : "status-pendente";

    const statusLabel =
      pub.status === "RESOLVIDO" ? "Resolvido" :
      pub.status === "EM_ANALISE" ? "Em análise" :
      "Pendente";

    return `
      <article class="post" data-id="${pub.id}">
        <div class="post-header">
          <div class="avatar">${iniciais(pub.nomeUsuario)}</div>
          <div class="post-meta">
            <span class="author">${pub.nomeUsuario}</span>
            <span class="info">${pub.nomeEspaco} • ${data}</span>
          </div>
          <span class="status-badge ${statusClass}">${statusLabel}</span>
        </div>
        <img class="post-image" src="" alt="Publicação">
        <div class="post-actions">
          <button class="like-btn" type="button" onclick="curtirPublicacao(${pub.id})">❤️ Curtir</button>
          <span class="likes-count" id="likes-${pub.id}" data-count="${pub.totalApoios || 0}">
            ${pub.totalApoios || 0} curtidas
          </span>
        </div>
        <p class="caption">${pub.descricao}</p>
      </article>
    `;
  }).join("");
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
  const status = document.getElementById("postStatus").value;
  const usuario = JSON.parse(localStorage.getItem("usuarioLogado")); 

  if (!descricao || !usuario) {
    return alert("Faça login e preencha o campo.");
  }

  const novaPublicacao = {
    usuarioId: usuario.id,
    nomeEspaco: nomeEspaco,
    descricao: descricao,
    status: status
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

document.addEventListener("DOMContentLoaded", carregarPublicacoes);
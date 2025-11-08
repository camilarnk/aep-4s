console.log("🌳 OcupaMais apoio.js carregado!");

// Curtir / descurtir publicação
async function curtirPublicacao(idPublicacao) {
  const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
  if (!usuario) return alert("Você precisa estar logado para curtir.");

  const botao = document.querySelector(`article[data-id="${idPublicacao}"] .like-btn`);
  const contador = document.getElementById(`likes-${idPublicacao}`);
  let valor = parseInt(contador.dataset.count || "0");

  if (botao.classList.contains("liked")) {
    // Descurtir
    botao.classList.remove("liked");
    valor = Math.max(0, valor - 1);
    contador.dataset.count = valor;
    contador.textContent = `${valor} curtidas`;

    // Remover apoio do banco
    await fetch(`http://localhost:8080/apoios/${idPublicacao}?idUsuario=${usuario.id}`, {
      method: "DELETE"
    });
  } else {
    // Curtir
    botao.classList.add("liked");
    valor++;
    contador.dataset.count = valor;
    contador.textContent = `${valor} curtidas`;

    // Salvar apoio no banco
    const apoio = {
      usuarioId: usuario.id,
      publicacaoId: idPublicacao
    };

    await fetch("http://localhost:8080/apoios", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(apoio)
    });
  }
}
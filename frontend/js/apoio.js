console.log("🌳 OcupaMais apoio.js carregado!");

// Curtir / descurtir publicação
async function curtirPublicacao(idPublicacao) {
  const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
  if (!usuario) return alert("Você precisa estar logado para curtir.");

  const botao = document.querySelector(`article[data-id="${idPublicacao}"] .like-btn`);
  const contador = document.getElementById(`likes-${idPublicacao}`);

  if (!botao || !contador) {
    console.warn(`⚠️ Botão ou contador não encontrados para publicação ${idPublicacao}`);
    return;
  }

  let valor = parseInt(contador.dataset.count || "0", 10);
  const jaCurtiu = botao.classList.contains("liked");

  try {
    if (jaCurtiu) {
      // Descurtir
      botao.classList.remove("liked");
      botao.textContent = "❤️ Curtir";
      valor = Math.max(0, valor - 1);
      contador.dataset.count = valor;
      contador.textContent = `${valor} curtidas`;

      await fetch(`http://localhost:8080/apoios/${idPublicacao}?idUsuario=${usuario.id}`, {
        method: "DELETE",
      });
    } else {
      // Curtir
      botao.classList.add("liked");
      botao.textContent = "💚 Curtido";
      valor++;
      contador.dataset.count = valor;
      contador.textContent = `${valor} curtidas`;

      await fetch("http://localhost:8080/apoios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ usuarioId: usuario.id, publicacaoId: idPublicacao }),
      });
    }
  } catch (erro) {
    console.error("❌ Erro ao curtir/descurtir:", erro);
  }
}
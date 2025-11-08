console.log("🌳 OcupaMais espaco.js carregado!");

// Carregar lista de espaços públicos (para futuras seleções)
async function carregarEspacos() {
  try {
    const resposta = await fetch("http://localhost:8080/espacos");
    if (!resposta.ok) throw new Error("Erro ao carregar espaços");
    const espacos = await resposta.json();
    console.log("Espaços carregados:", espacos);
  } catch (erro) {
    console.error("Erro ao buscar espaços:", erro);
  }
}

document.addEventListener("DOMContentLoaded", carregarEspacos);
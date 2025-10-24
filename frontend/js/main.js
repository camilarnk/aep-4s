console.log("OcupaMais frontend carregado!");

// Exemplo: carregar publicações (futuro)
async function carregarPublicacoes() {
  const resposta = await fetch("http://localhost:8080/publicacoes");
  const dados = await resposta.json();
  console.log(dados);
}
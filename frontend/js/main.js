console.log("🌳 OcupaMais main.js carregado!");

// Função para login
document.getElementById("formLogin").addEventListener("submit", async (e) => {
  e.preventDefault();

  const dados = {
    email: e.target.email.value,
    senha: e.target.senha.value
  };

  try {
    const resposta = await fetch("http://localhost:8080/usuarios/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dados)
    });

    if (!resposta.ok) {
      alert("Email ou senha incorretos.");
      return;
    }

    const usuario = await resposta.json();
    localStorage.setItem("usuarioLogado", JSON.stringify(usuario));
    window.location.href = "usuario/publicacoes.html";
  } catch (erro) {
    console.error("Erro ao conectar com o backend:", erro);
    alert("Erro de conexão com o servidor.");
  }
});

// Função global para verificar se está logado
function getUsuarioLogado() {
  return JSON.parse(localStorage.getItem("usuarioLogado"));
}

// Função global para logout
function logout() {
  localStorage.removeItem("usuarioLogado");
  window.location.href = "../index.html";
}
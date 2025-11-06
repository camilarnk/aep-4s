console.log("🌳 OcupaMais usuario.js carregado!");

// Verifica se há usuário logado
const usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));

if (!usuarioLogado) {
  alert("Você precisa estar logado para acessar o perfil!");
  window.location.href = "../index.html";
} else {
  // Preenche as informações do usuário na tela
  document.getElementById("userName").textContent = usuarioLogado.nome || "Usuário";
  document.getElementById("userEmail").textContent = usuarioLogado.email || "Email não informado";
  document.getElementById("userRole").textContent =
    usuarioLogado.tipo?.toUpperCase() === "ADMIN" ? "ADMIN" : "USUÁRIO";
}

// Troca de foto
const changeBtn = document.getElementById("changePhotoBtn");
const fileInput = document.getElementById("avatarInput");
const avatarImg = document.getElementById("avatarImg");

if (changeBtn && fileInput && avatarImg) {
  changeBtn.addEventListener("click", () => fileInput.click());
  fileInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = (evt) => (avatarImg.src = evt.target.result);
    reader.readAsDataURL(file);
  });
}

// Editar "Sobre"
const aboutText = document.getElementById("aboutText");
const aboutInput = document.getElementById("aboutInput");
const editBtn = document.getElementById("editAboutBtn");
const saveBtn = document.getElementById("saveAboutBtn");
const cancelBtn = document.getElementById("cancelAboutBtn");

// Carregar texto salvo anteriormente
const textoSalvo = localStorage.getItem("perfilSobre");
if (textoSalvo) aboutText.textContent = textoSalvo;

if (editBtn && saveBtn && cancelBtn && aboutInput && aboutText) {
  editBtn.addEventListener("click", () => {
    aboutInput.value = aboutText.textContent;
    toggleEdit(true);
  });

  saveBtn.addEventListener("click", () => {
    aboutText.textContent = aboutInput.value || "Sem descrição.";
    localStorage.setItem("perfilSobre", aboutText.textContent);
    toggleEdit(false);
  });

  cancelBtn.addEventListener("click", () => toggleEdit(false));
}

function toggleEdit(editando) {
  aboutText.style.display = editando ? "none" : "block";
  aboutInput.style.display = editando ? "block" : "none";
  editBtn.style.display = editando ? "none" : "inline-block";
  saveBtn.style.display = editando ? "inline-block" : "none";
  cancelBtn.style.display = editando ? "inline-block" : "none";
}
console.log("🌳 OcupaMais cadastro.js carregado!");

document.getElementById("formCadastro").addEventListener("submit", async (e) => {
    e.preventDefault();

    const dados = {
        nome: e.target.nome.value.trim(),
        email: e.target.email.value.trim(),
        senha: e.target.senha.value.trim(),
        tipo: e.target.usuariotipo.value,
        bairro: e.target.bairro.value.trim()
    };

    // validando dados no frontend
    if (dados.nome.length < 2) {
        alert("O nome deve ter pelo menos 2 caracteres.");
        return;
    }

    if (!dados.email.includes("@") || !dados.email.includes(".")) {
        alert("Digite um email válido.");
        return;
    }

    if (dados.senha.length < 4) {
        alert("A senha deve ter pelo menos 4 caracteres.");
        return;
    }

    if (!dados.tipo) {
        alert("Selecione o tipo de usuário.");
        return;
    }

    if (dados.bairro.length < 2) {
        alert("Digite um bairro válido.");
        return;
    }

    // enviando dados para o backend
    try {
        const resposta = await fetch("http://localhost:8080/usuarios", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados)
        });

        const respostaTexto = await resposta.text();

        if (!resposta.ok) {
            // mensagens vindas do backend (400 ou 409)
            alert(respostaTexto || "Erro ao cadastrar usuário.");
            return;
        }

        const usuario = JSON.parse(respostaTexto); // caso sucesso (201)
        localStorage.setItem("usuarioLogado", JSON.stringify(usuario));
        alert("Cadastro realizado");
        if (usuario.tipo === "ADMIN") {
            window.location.href = "../admin/relatorios.html";
        } else {
            window.location.href = "publicacoes.html";
        }

    } catch (erro) {
        console.error("Erro: ", erro);
        alert("Erro ao tentar conectar com o servidor.");
    }
});
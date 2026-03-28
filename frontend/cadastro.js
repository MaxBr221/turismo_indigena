
async function cadastro() {

    const nome = document.querySelector("#nome").value;
    const telefone = document.querySelector("#telefone").value;
    const login = document.querySelector("#login").value;
    const senha = document.querySelector("#senha").value;
    console.log("tá passando");

    const response = await fetch("http://localhost:8080/auth/register", {
        method: "POST",
        headers: {
            "content-type": "application/json"

        },
        body: JSON.stringify({
            nome: nome,
            telefone: telefone,
            login: login,
            senha: senha
        })

    });
    const data = await response.text;

    if(response.ok){
        alert("Cadastro feito com sucesso");
    }else{
        alert("Erro no cadastro" + data);
    }
}
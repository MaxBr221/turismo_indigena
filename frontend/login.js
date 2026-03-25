async function login() {
    const email = document.querySelector("#email").value;
    const senha = document.querySelector("#senha").value;

    const response = await fetch("http://localhost:8080/auth/login",{
        method: "POST",
        headers: {
            "content-type": "application/json"
        
        },
        body: JSON.stringify({
            email: email,
            senha: senha

        })

        
    });
    const data = await response.text();

    if(response.ok){
        alert("Login feito com sucesso!");
    }else{
        alert("Erro" + data);
    }
    
}
async function login(){
    const login = document.querySelector("#email").value;
    const senha = document.querySelector("#senha").value;

    console.log("indo pra fetch");

    const response = await fetch("http://localhost:8080/auth/login",{
        method: "POST",
        headers: {
            "content-type": "application/json"
            
        
        },
        
        body: JSON.stringify({
            login: login,
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
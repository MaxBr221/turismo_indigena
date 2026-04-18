console.log("Arquivo login.js carregado com sucesso!");
document.addEventListener('DOMContentLoaded', () => {
    const btn = document.getElementById('btnLogin');
    if(btn){
        btn.addEventListener('click', realizarLogin);
    }
});

async function realizarLogin(){
    const login = document.querySelector("#login").value;
    const senha = document.querySelector("#senha").value;

    console.log("indo pra fetch");
    try{
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

        if(response.ok){
            const data = await response.json();
            console.log("token: ", data.token);  

            localStorage.setItem('token', data.token);
            window.location.href = "painelPrincipal.html";
        
        }else{
            alert("Login inválido" + data);
        }  
    }catch(error){
        console.error("Erro na requesição de login:", error);
        alert("Não foi possivel conectar ao servidor");
    }
    
}

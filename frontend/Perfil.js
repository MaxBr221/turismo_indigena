addEventListener('DOMContentLoaded', function(){
    perfil();
})

const perfil = async() =>{

    try {
        const token = localStorage.getItem('token');

        if(!token){
            window.location.href = 'login.html';
            return;

        }
        const response = await fetch ("http://localhost:8080/users/mePerfil",{
            method: 'GET',
            headers: {
                'authorization': `Bearer ${token}`
        }

    });

        if(response.ok){
            console.log("busca feita com sucesso");
            const informacoes = await response.json();
            console.log(informacoes);
            listaInfo(informacoes);
          
        }else{
            if(response.status == 403 || response.status == 404){
                console.log("Erro ao buscar");
                alert("Sessão expirada, faça o login novamente");
                window.location.href('login.html');

            }
            throw new Error("erro ao fazer busca");
        }
        

        
    } catch (error) {
        throw new Error("Erro no sistema");
        
    }
    
}

const listaInfo = (dados) =>{
    const lista = document.getElementById('meu-perfil');
    console.log("Entrou pra criar lista");

  
    const linha = document.createElement('ul');
    linha.innerHTML = `
        <li><strong>Nome:</strong>${dados.nome}</li>
        <li><strong>Telefone:</strong>${dados.telefone}</li>
        <li><strong>Nome:</strong>${dados.login}</li>
    `;
    lista.innerHTML = "";
    lista.appendChild(linha);
    
    
}
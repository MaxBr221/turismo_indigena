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
        const response = await fetch ("http://localhost:8080/meuPerfil/me",{
        method: 'GET',
        headers: {
            'authorization': `Bearer ${token}`
        }

    });

        if(response.ok){
            console.log("busca feita com sucesso");
        }else{
            if(response.status == 403 || response.status == 404){
                console.log("Erro ao buscar");
                alert("Sessão expirada, faça o login novamente");
                window.location.href('login.html');

            }
            throw new Error("erro ao fazer busca");
        }

        const informacoes = await response.json();
        console.log("armazenando informações");
        listaInfo(informacoes);

        
    } catch (error) {
        throw new Error("Erro no sistema");
        
    }
    
}

const listaInfo = async(dados) =>{
    const lista = document.getElementById('meu-perfil');
    lista.innerHTML = "";
    const informacoes = dados.content;

    informacoes.forEach(info => {
        const linha = document.createElement('lu');
        linha.innerHTML = `
        <li><strong>Nome:</strong>${info.nome}</li>
        <li><strong>Telefone:</strong>${info.telefone}</li>
        <li><strong>Nome:</strong>${info.login}</li>
        `;
        lista.appendChild(linha);
        
    });
}
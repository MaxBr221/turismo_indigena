document.addEventListener('DOMContentLoaded', function(){
    restaurantes();
});

async function restaurantes() {
    
    try {
        const token = localStorage.getItem('token');

        if(!token){
            window.location.href = 'login.html';
            return;

        }
        const response = await fetch("http://localhost:8080/restaurantes/restaurantePaginacao",{
            method: 'GET',
            headers: {
                'authorization': `Bearer ${token}`

        }
    });
    

        if(response.ok){
            console.log("busca com sucesso!");
        }else{
            if(response.status == 403 || response.status == 401){
                console.log("Erro ao buscar");
                alert("Sessão expirada, faça o login novamente");
                window.location.href('login.html');

            }
            throw new Error("Erro ao fazer busca no sistema");
            
        }

        const salvos = await response.json();
        console.log("Guardando restaurantes");
        listarRestaurantes(salvos);


    } catch (error) {
        console.log("erro na listagem de restaurante: " + error );
        alert("erro no sistema: " + error);
    }
    
}

function listarRestaurantes(dados){
    const tbody = document.getElementById('listarRestaurantes');
    tbody.innerHTML = "";
    const restaurantes = dados.content;


    restaurantes.forEach(r => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${r.nome}</td>
            <td>${r.descricao}</td>
            <td>${r.localização}</td>
            <td>${r.horário}</td>
            <td>${r.telefone}</td>
            <td>${r.redeSocial || 'sem no momento'}</td>
            <td>${r.avaliacoes || '0'} ⭐</td>
        `;
        tbody.appendChild(linha);
    });
    
}
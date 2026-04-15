document.addEventListener('DOMContentLoaded', function(){
    restaurantes();
});

async function restaurantes() {
    
    try {
        const response = await fetch("http://localhost:8080/restaurantes/restaurantePaginacao",{
            method: 'GET',
            headers: {
                'content-type': 'application/json'

        }
    });

        if(response.ok){
            console.log("busca com sucesso!");
        }else{
            console.log("Erro ao buscar");
            throw new Error('Erro no sistema: ${response.status}');
        }

        const salvos = await response.json();
        console.log("Guardando restaurantes");
        listarRestaurantes(salvos);


    } catch (error) {
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
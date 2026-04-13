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
            alert("Erro ao fazer a busca");
        }

        const salvos = response.json;
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


    restaurantes.array.forEach(r => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>Nome</td>
            <td>Descrição</td>
            <td>Localização</td>
            <td>Horário</td>
            <td>Telefone</td>
            <td>Rede Social</td>
            <td>Avaliações</td>
        `
        tbody.appendChild(linha);
    });
    
}
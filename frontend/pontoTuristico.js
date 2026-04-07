document.addEventListener('DOMContentLoaded', function() {
    pontos();
});

async function pontos() {

    try {
        const response = await fetch("http://localhost:8080/pontoTuristico/pontos", {
        method: 'GET',
        headers: {
            'content-type': 'application/json'

        }
        
    });
        if(response.ok){
            console.log("Tá tudo ok");
        
        }else{
            throw new Error("Erro ao listar os pontos turisticos: " + response.statusText);
        }

        const listaPontos = await response.json();
        console.log(listaPontos);
        exibirPontos(listaPontos);

    } catch (error) {
        console.error("Erro na listagem de pontos turisticos: " + error);
        alert(error);
    }
}
function exibirPontos(dados) {
    const tbody = document.getElementById('lista-pontos');
    tbody.innerHTML = "";
    const pontos = dados.content;



    pontos.forEach(ponto => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${ponto.nome}</td>
            <td>${ponto.local}</td>
            <td>${ponto.informacoes}</td>
            <td>${ponto.avaliacoes || '0'} ⭐</td>
        `;
        tbody.appendChild(linha);
    });

}

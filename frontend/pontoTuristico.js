document.addEventListener('DOMContentLoaded', function() {
    pontos();
});

async function pontos() {
    try {
        const token = localStorage.getItem('token');
        
        if(!token){
            window.location.href = 'login.html';                
            return;

        }
        const response = await fetch("http://localhost:8080/pontoTuristico/pontos", {
            method: 'GET',
            headers: {
                
                'authorization': 'Bearer ' + token
        }
        
    });
        if(response.ok){
            console.log("Tá tudo ok");
            console.log("token: " + token);

            const listaPontos = await response.json();
            // 4. VERIFICAÇÃO DE ESTRUTURA (Spring usa .content)
            if (listaPontos && listaPontos.content) {
                exibirPontos(listaPontos.content);
            } else {
                exibirPontos(listaPontos); // Caso não seja paginado
            }
           
        
        }else{

            if(response.status == 403 || response.status == 404){
                console.log("Erro ao buscar");
                alert("Sessão expirada, faça o login novamente");
                window.location.href('login.html');

            }
            throw new Error("Não existe ponto turistico cadastrado");
        }

       
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
 let atribute = document.getElementById('lista-pontos');
            atribute.setAttribute('style', 'background-color: blue');

document.addEventListener('DOMContentLoaded', function(){
    Guide();//  Ela garante que os guias turísticos só apareçam quando o "esqueleto" do site estiver 
            // pronto para recebê-los--!>

            //await -> permanece até que a etapa seja comprida.
});
const Guide = async() => {
    try {
        const token = localStorage.getItem('token');

        if(!token){
            window.location.href = 'login.html';
            return;
        }
        const response = await fetch ("http://localhost:8080/guide", {
        method: 'GET',
        headers: {
            'authorization': `Bearer ${token}`
        }
        
    });
        if(response.ok){
            console.log("listagem feita com sucesso!");
        }else{
            if(response.status == 403 || response.status == 404){
                console.log("Erro ao buscar");
                alert("Sessão expirada, faça o login novamente");
                window.location.href('login.html');
            }
            throw new Error("Erro na busca de guias")
        }

        const listaDeGuias = await response.json();
        console.log(listaDeGuias);
        listarGuias(listaDeGuias);

        
    } catch (error) {
        alert("Erro no sistema");        
    }

}

const listarGuias = (dados) =>{
    const tbody = document.getElementById('lista-de-guias');
    tbody.innerHTML = "";
    const guide = dados.content;

    guide.forEach(guide => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${guide.nome}</td>
            <td>${guide.telefone}</td>
            <td>${guide.descricao}</td>
        `;
       tbody.appendChild(linha); 
    });
}
document.addEventListener('DOMContentLoaded', ()=> {
    const button = document.getElementById('pontos')
    if(button){
        button.addEventListener('load', pontos);
    }
})

async function pontos() {

    
    try {
        const response = await fetch("http://localhost:8080/pontoTuristico/pontos", {
        method: "GET",
        headers: {
            "content-type": "application/json"

        }
        
    });
        if(response.ok){
            console.log("Tá tudo ok");
        }else{
            throw new Error("Erro ao listar os pontos turisticos: " + response.statusText);
        }

        const listaPontos = await response.json;
        console.log(listaPontos);

    } catch (error) {
        console.error("Erro na listagem de pontos turisticos: " + error);
        alert(error);
    }
    
}
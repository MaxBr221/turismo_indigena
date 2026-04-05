document.addEventListener('DOMContentLoaded', ()=> {
    const button = document.getElementById("pontos")
    if(button){
        button.addEventListener('load', pontos);
    }
})

async function pontos() {
    const nome = document.getElementById("#nome");
    const local = document.getElementById("#local");
    const informacoes = document.getElementById("#informacoes");
    const avaliacoes = document.getElementById("#avaliacoes");
    
    try {
        const response = await fetch("http://localhost:8080/pontoTuristico/pontos", {
        method: "GET",
        headers: {
            "content-type": "application/json"

        },
        body: JSON.stringify({
            nome: nome,
            local: local,
            informacoes: informacoes,
            avaliacoes: avaliacoes
        })
    });
        const data = await response.text;

        if(response.ok){
            console.log("listando pontos com sucesso!");
        }else{
            alert("ERRO: " + data);
        }
        
    } catch (error) {
        console.error("Erro na listagem de pontos turisticos: " + error);
        alert(error);
    }
    
}
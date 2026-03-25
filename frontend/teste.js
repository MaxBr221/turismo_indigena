const usuario = {
    nome: "Maxsuel",
    idade: 20,
    Cidade: "Baia da Traição",
    Pais: "Brasil"
}
const usuario2= {
    nome: "Jota",
    idade: 20,
    Cidade: "Baia da Traição",
    Pais: "Brasil"
}

const array = [
    {
    nome: "Maxsuel",
    idade: 20,
    Cidade: "Baia da Traição",
    Pais: "Brasil"
}, {
    nome: "Jota",
    idade: 20,
    Cidade: "Baia da Traição",
    Pais: "Brasil"
}
]
console.log(array)

if(usuario.idade >= 10){
    console.log("Passou");
}else{
    console.log("Não passou")
}

function printMeuNome(nome){
    console.log(nome)
}

printMeuNome("Maxsuel")


const funcao = () =>{ //função moderna
    console.log("Função arrow")
}

funcao()


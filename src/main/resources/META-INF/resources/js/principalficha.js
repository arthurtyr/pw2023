function criarFicha(nome, nivel, destreza, constituicao, inteligencia){
    return new Request("http://localhost:8080/criarficha", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "nome": nome,
            "nivel": nivel,
            "destreza": destreza,
            "constituicao": constituicao,
            "inteligencia": inteligencia
        }),
    });
}
function criarRequisicao(){
    return criarFicha(document.getElementById("nome").value,document.getElementById("nivel").value,document.getElementById("destreza").value,document.getElementById("constituicao").value,document.getElementById("inteligencia").value)
}

function salvar(){
    const requisicao = criarRequisicao();
    fetch(requisicao)
        .then((response)=>{
            if(response.status === 200){
                return response.json();
            } else{
                throw new Error("Ocorreu algum erro no servidor")
            }
        })
        .then(json =>{
            alert(json.mensagem);
        })
}
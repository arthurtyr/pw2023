function validar_formulario(){
    return document.getElementById("email").value !== '' && document.getElementById("senha").value !== '';
}

function criarRequisicao(email,senha){
    return new Request("http://localhost:8080/autenticar", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "email": email,
            "senha": senha
        }),
    });
}
function autenticar(){
    if(validar_formulario()){
        var requisicao = criarRequisicao(document.getElementById("email").value,document.getElementById("senha").value)
        fetch(requisicao)
            .then((response) => {
                if(response.status === 200) {
                    return response.json();
                } else {
                    throw new Error("Ocorreu algum errno no servidor");
                }
            })
            .then(json => {
                console.log(JSON.stringify(json));
                if(json.mensagem === "Usuário não autenticado!"){
                    alert("Usuário não autenticado!!")
                }
            });
    }else
        alert('Os campos e-mail e senha são obrigatórios Verifique o formulário')

}
// colocar uma mensagem de erro quando nao autenticar o usuario

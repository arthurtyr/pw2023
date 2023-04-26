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
        document.getElementById("vazio").style.display = "none";
        var requisicao = criarRequisicao(document.getElementById("email").value,document.getElementById("senha").value)
        fetch(requisicao)
            .then((response) => {
                if(response.status === 200) {
                    return response.json();
                } else {
                    throw new Error("Ocorreu algum erro no servidor");
                }
            })
            .then(json => {
                console.log(JSON.stringify(json));
                if(json.mensagem === "Usuário não autenticado!"){
                    document.getElementById("alerta-email").style.display = "block";
                }else if (json.mensagem === "Usuário autenticado!"){
                    window.location.href = window.location.origin+'/principal';
                }
            });
    }else
        document.getElementById("vazio").style.display = "block";
        /*ALTERANDO O DISPLAY DO AVISO PARA BLOCK, QUE ANTES ESTAVA NONE*/

}
// colocar uma mensagem de erro quando nao autenticar o usuario

function validar_formulario(){
    return document.getElementById("email").value !== '' && document.getElementById("senha").value !== '';
}

function autenticar(){
    if(validar_formulario()){
        var requisicao = criarRequisicao(document.getElementById("email").value,document.getElementById("senha".value))
        fetch(requisicao)
            .then((response) => {
                if(response.status === 200) {
                    return response.json();
                } else {
                    throw new Error("Ocorreu algum errno no servidor");
                }
            })
            .then(json => console.log(JSON.stringify(json)));
    }else
        alert('Os campos e-mail e senha são obrigatórios Verifique o formulário')

}

function criarRequisicao(email,senha){
    return new Request("https://localhost:8080/autenticar", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "aplication/json"
        },
        body: JSON.stringify({
            "email": email,
            "senha": senha
        })
    });
}
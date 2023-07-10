function validar(){
    return document.getElementById("nome").value !== "";
}

function criarPerfil(nome, acessolog, excluirusuario){
    return new Request("http://localhost:8080/criarperfil", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "nome": nome,
            "acessolog": acessolog,
            "excluirusuario": excluirusuario
        }),
    });
}

function check(){
    if(document.getElementById("acessolog").checked){
        document.getElementById("acessolog").value = "sim";
    }else{
        document.getElementById("acessolog").value = "nao";
    }
    if(document.getElementById("excluirusuario").checked){
        document.getElementById("excluirusuario").value = "sim";
    }else{
        document.getElementById("excluirusuario").value = "nao";
    }
}

function perficilizar() {
        if(validar()){
            check();
            const perfil = criarPerfil(document.getElementById("nome").value,document.getElementById("acessolog").value,document.getElementById("excluirusuario").value);
            limpar();
            fetch(perfil)
                .then((response) =>{
                    if(response.status === 200) {
                        return response.json();
                    } else{
                        throw new Error("Ocorreu algum erro no servidor");
                    }
                })
        }
        else {
            alert("O campo Nome do perfil é obrigatório");
        }
}

function limpar(){
    document.getElementById("nome").value = "" ;
    document.getElementById("acessolog").checked = false;
    document.getElementById("excluirusuario").checked = false;
}

function validar_formulario(){
    if (document.getElementById("email").value !== ''
        && document.getElementById("senha").value !== ''
        && document.getElementById("nome").value !== ''
        && document.getElementById("confsenha").value !== ''){
        if(document.getElementById("senha").value === document.getElementById("confsenha").value){
            return true;
        }

    }else{
        return false;

    }
}

function criarCadastro(email, nome, senha, confsenha){
    return new Request("http://localhost:8080/cadastrar", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "email": email,
            "nome": nome,
            "senha": senha,
            "confsenha": confsenha
        }),
    });
}

function cadastrar() {
    if(validar_formulario()){
        var cadastro = criarCadastro(document.getElementById("email").value,document.getElementById("nome").value,document.getElementById("senha").value,document.getElementById("confsenha").value);
        fetch(cadastro)
            .then((response) =>{
                if(response.status === 200){
                    return response.json();
                } else {
                    throw new Error("Ocorreu algum erro no servidor");
                }
            })
            .then(json =>{
                console.log(JSON.stringify(json));
                if(json.mensagem === "Usuario autenticado"){
                    alert("Cadastrado entre aspas");
                }else if(json.mensagem === "Usuario nao autenticado"){
                    alert("Nao cadastrado entre aspas");
                }
            })
    }else{
        alert("Nao validado");
    }
}
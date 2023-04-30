
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
function getCadastro(){
    return new Request("http://localhost:8080/cadastrar", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}
function limpar(){
    return document.getElementById("email").value = "" ,
        document.getElementById("nome").value = "",
        document.getElementById("senha").value = "",
        document.getElementById("confsenha").value = "";
}
function cadastrar() {
    if(validar_formulario()){
        var cadastro = criarCadastro(document.getElementById("email").value,document.getElementById("nome").value,document.getElementById("senha").value,document.getElementById("confsenha").value);
        limpar();
        fetch(cadastro)
            .then((response) =>{
                if(response.status === 201){
                    return response.json();
                } else if(response.status === 200) {
                    return response.json();
                } else{
                    throw new Error("Ocorreu algum erro no servidor");
                }
            })
            .then(get);


    }else{
        alert("Nao validado");

    }
}
function get(){
    var getcadastro = getCadastro();
    fetch(getcadastro)
        .then((response) =>{
            if(response.status === 200){
                return response.json();
            } else{
                throw new Error("Ocorreu algum erro no servidor");
            }
        })
        .then(json =>{
            const cadinfo = JSON.stringify(json);
            const obj = JSON.parse(cadinfo);
            criarTabela(obj.email,obj.nome,obj.senha);

        })
}
function criarTabela(email, nome, senha){
    var tabela = document.getElementById("tabelaCadastro");
    var corpoTabela = document.getElementById("corpoTabelaCadastro");

    var linha = corpoTabela.insertRow();
    var lemail = linha.insertCell(0);
    var lnome = linha.insertCell(1);
    var lsenha = linha.insertCell(2);

    lemail.innerText = email;
    lnome.innerText = nome;
    lsenha.innerText = senha;

}
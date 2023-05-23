
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
//função que cria um objeto Request, que possui o caminho que será acessado no servidor(input) e
//um objeto init que seta as configurações da requisição
//por ser um POST, pode possuir um body, que é corpo da requisição.
//essa requisição é responsável por fazer o cadastro
function criarCadastro(email, nome, senha){
    return new Request("http://localhost:8080/cadastrar", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "email": email,
            "nome": nome,
            "senha": senha
        }),
    });
}

function limpar(){
        document.getElementById("email").value = "" ;
        document.getElementById("nome").value = "";
        document.getElementById("senha").value = "";
        document.getElementById("confsenha").value = "";
}
function cadastrar() {
    if(validar_formulario()){
        //criar um obj criarCadastro contendo os dados do html(alem do input e do init)
        const cadastro = criarCadastro(document.getElementById("email").value,document.getElementById("nome").value,document.getElementById("senha").value);
        limpar();
        //faz a requisiçao de cadastro
        fetch(cadastro)
            //apos retornar o response sera executado o codigo abaixo
            .then((response) =>{
                //se a operação der certo a response.status sera igual a 201, logo retorna o response.json
                if(response.status === 200) {
                    return response.json();
                } else{//senao lança um erro
                    throw new Error("Ocorreu algum erro no servidor");
                }
            })
            //quando a operação acima terminar executa a função get
            .then(json => {
                alert(json.mensagem);
                window.location.href = window.location.origin+json.url;
            })
    }else{
        alert("Não validado");

    }
}

function criarTabela(email, nome, senha){
    const corpoTabela = document.getElementById("corpoTabelaCadastro");

    const linha = corpoTabela.insertRow();
    const lemail = linha.insertCell(0);
    const lnome = linha.insertCell(1);
    const lsenha = linha.insertCell(2);

    lemail.innerText = email;
    lnome.innerText = nome;
    lsenha.innerText = senha;

}

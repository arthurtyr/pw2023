function criarPerfil(nomeperfil, trocanomeperfil, acessolog, criarficha){
    return new Request("http://localhost:8080/criarperfil", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "nomeperfil": nomeperfil,
            "trocanomeperfil": trocanomeperfil,
            "acessolog": acessolog,
            "criarficha": criarficha
        }),
    });
}

function getPerfil(){
    return new Request("http://localhost:8080/criarperfil", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}

function perficilizar() {
    if(validar_formulario()){
        const perfil = criarPerfil(document.getElementById("nomeperfil").value,document.getElementById("trocanomeperfil").value,document.getElementById("acessolog").value,document.getElementById("criarficha").value);
        limpar();
        fetch(perfil)
            .then((response) =>{
                if(response.status === 201) {
                    return response.json();
                } else{
                    throw new Error("Ocorreu algum erro no servidor");
                }
            })
            .then(get)

    }else{
        alert("Nao validado");
    }
}

function get(){
    const getperfil = getPerfil();
    fetch(getperfil)
        .then((response) =>{
            if(response.status === 200){
                return response.json();
            }else{
                throw new Error("Ocorreu algum erro no servidor");
            }
        })
        .then(json =>{
            if(JSON.stringify(json) === "{\"mensagem\":\"utilizado\"}"){
                alert("nome de perfil já utilizado");
            }else{
                const perfilinfo = JSON.stringify(json);
                const obj = JSON.parse(perfilinfo);
                criarTabela(obj.nomeperfil,obj.trocanomeperfil,obj.acessolog,obj.criarficha);
            }
        })
}

function criarTabela(nomeperfil, trocanomeperfil, acessolog, criarficha){
    const corpoTabela = document.getElementById("corpoTabelaCadastro");

    const linha = corpoTabela.insertRow();
    const lnomeperfil = linha.insertCell(0);
    const ltrocanomeperfil = linha.insertCell(1);
    const lacessolog = linha.insertCell(2);
    const lcriarficha = linha.insertCell(3);

    lnomeperfil.innerText = nomeperfil;
    ltrocanomeperfil.innerText = trocanomeperfil;
    lacessolog.innerText = acessolog;
    lcriarficha.innerText = criarficha;
}

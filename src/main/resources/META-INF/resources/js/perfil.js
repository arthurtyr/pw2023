function criarPerfil(username){
    return new Request("http://localhost:8080/perfil", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "username": username,
        }),
    });
}

function getPerfil(){
    return new Request("http://localhost:8080/perfil", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}

function criarTabela(username){
    const corpoTabela = document.getElementById("corpoTabelaCadastro");

    const linha = corpoTabela.insertRow();
    const lusername = linha.insertCell(0);

    lusername.innerText = username;
}
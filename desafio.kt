enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel = Nivel.INTERMEDIARIO, var concluido: Boolean = false)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (!inscritos.contains(usuario)) {
            inscritos.add(usuario)
            println("Usuário ${usuario.nome} matriculado na formação $nome.")
        } else {
            println("Usuário ${usuario.nome} já está matriculado nesta formação.")
        }
    }

    fun desmatricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            inscritos.remove(usuario)
            println("Usuário ${usuario.nome} removido da formação $nome.")
        } else {
            println("Usuário ${usuario.nome} não está matriculado nesta formação.")
        }
    }

    fun exibirConteudosDisponiveis() {
        println("Conteúdos disponíveis na formação $nome:")
        for (conteudo in conteudos) {
            println("${conteudo.nome} - Nível: ${conteudo.nivel} - Duração: ${conteudo.duracao} minutos - Concluído: ${conteudo.concluido}")
        }
    }

    fun verificarProgresso(usuario: Usuario) {
        val conteudosConcluidos = conteudos.count { it.concluido }
        val totalConteudos = conteudos.size
        val percentualConcluido = (conteudosConcluidos.toDouble() / totalConteudos.toDouble()) * 100

        println("Progresso de ${usuario.nome} na formação $nome: $percentualConcluido% concluído.")
    }

    fun marcarConteudoConcluido(usuario: Usuario, conteudoNome: String) {
        val conteudo = conteudos.find { it.nome == conteudoNome }

        if (conteudo != null) {
            conteudo.concluido = true
            println("Conteúdo ${conteudo.nome} marcado como concluído para ${usuario.nome}.")
        } else {
            println("Conteúdo $conteudoNome não encontrado na formação $nome.")
        }
    }
}

fun main() {
    val usuario1 = Usuario("João")
    val usuario2 = Usuario("Maria")

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 45, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos", 60, Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Desenvolvimento Android", 90, Nivel.DIFICIL)

    val formacaoKotlin = Formacao("Formação Kotlin", listOf(conteudo1, conteudo2, conteudo3))

    // Simulando matrícula e desmatrícula
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)

    // Simulando exibição de conteúdos disponíveis
    formacaoKotlin.exibirConteudosDisponiveis()

    // Simulando progresso e marcação de conteúdo como concluído
    formacaoKotlin.verificarProgresso(usuario1)
    formacaoKotlin.marcarConteudoConcluido(usuario1, "Introdução ao Kotlin")
    formacaoKotlin.verificarProgresso(usuario1)
}

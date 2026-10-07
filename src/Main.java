void main() {
    Set<Pessoa> banco = new LinkedHashSet<>();
    banco.add(new Pessoa(1, "Isabella", 17));
    banco.add(new Pessoa(2, "Pedro", 62));
    banco.add(new Pessoa(3, "Nohana", 25));
    banco.add(new Pessoa(4, "Laraynny", 18));
    banco.add(new Pessoa(5, "Carlos", 71));

    Set<Pessoa> cache = new LinkedHashSet<>();

    while (true) {
        int idBusca = Integer.parseInt(IO.readln("Digite o ID do usuário que deseja buscar (ou -1 para sair): "));

        if (idBusca == -1) {
            break;
        }

        // 1. Procura primeiro no cache
        Pessoa pessoaCache = Pessoa.buscarPorId(cache, idBusca);

        if (pessoaCache != null) {
            IO.println("Pessoa encontrada no cache: " + pessoaCache);
        } else {
            // 2. Se não estiver no cache, procura no banco
            Pessoa pessoaBanco = Pessoa.buscarPorId(banco, idBusca);

            if (pessoaBanco != null) {

                if (cache.size() >= 10) {
                    Pessoa maisAntiga = cache.iterator().next();
                    cache.remove(maisAntiga);
                    IO.println("Cache lotado, a pessoa mais antiga foi removida: " + maisAntiga.getNome());
                }

                cache.add(pessoaBanco);
                IO.println("Pessoa buscada no banco e adicionada ao cache: " + pessoaBanco);
            } else {
                // 3. Se não encontrou no banco, cadastra a nova pessoa
                IO.println("Pessoa com ID " + idBusca + " não encontrada no banco");
                String nome = IO.readln("Digite o nome da nova pessoa: ");
                int idade = Integer.parseInt(IO.readln("Digite a idade da nova pessoa: "));

                Pessoa novaPessoa = new Pessoa(idBusca, nome, idade);
                banco.add(novaPessoa);

                // Limpa o cache se necessário e adiciona a nova pessoa
                if (cache.size() >= 10) {
                    Pessoa maisAntiga = cache.iterator().next();
                    cache.remove(maisAntiga);
                    IO.println("Cache lotado, a pessoa mais antiga foi removida: " + maisAntiga.getNome());
                }

                cache.add(novaPessoa);
                IO.println("Nova pessoa criada e adicionada ao banco e ao cache: " + novaPessoa);
            }
        }
    }
}
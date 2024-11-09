package com.desenvolvimento.services;

import com.desenvolvimento.domains.Autor;
import com.desenvolvimento.domains.Editora;
import com.desenvolvimento.domains.Livro;
import com.desenvolvimento.domains.enums.Conservacao;
import com.desenvolvimento.domains.enums.Status;
import com.desenvolvimento.repositories.AutorRepository;
import com.desenvolvimento.repositories.EditoraRepository;
import com.desenvolvimento.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalTime.now;

@Service
public class DBService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void initDB (){
        Autor autor01 = new Autor(null, "Machado de Assis", "RG");
        Editora editora01 = new Editora(null, "60.657.5740001-69", "Editora do Brasil S.A.");

        Autor autor02 = new Autor(null, "Clarice Lispector", "RG");
        Editora editora02 = new Editora(null, "62.136.3040001-38", "Editora Moderna Ltda.");

        Livro livro01 = new Livro(null, "Memórias Póstumas de Brás Cubas", "3268759642385", 480,
                LocalDate.now(), new BigDecimal("39.90").setScale(2), Status.NAOLIDO, Conservacao.BOM, autor01, editora01);

        Livro livro02 = new Livro(null, "A Hora da Estrela", "8569423674852", 88, LocalDate.now(), new BigDecimal("29.90").setScale(2),
                Status.NAOLIDO, Conservacao.BOM, autor02, editora02);

        Livro livro03 = new Livro(null, "Livrooooooo", "2269423671111", 11, LocalDate.now(), new BigDecimal("30.00").setScale(2),
                Status.NAOLIDO, Conservacao.BOM, autor02, editora01);

    autorRepository.save(autor01);
    autorRepository.save(autor02);
    editoraRepository.save(editora01);
    editoraRepository.save(editora02);
    livroRepository.save(livro01);
    livroRepository.save(livro02);
    livroRepository.save(livro03);
    }

}

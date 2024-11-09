package com.desenvolvimento.services;

import com.desenvolvimento.domains.Autor;
import com.desenvolvimento.domains.Editora;
import com.desenvolvimento.domains.Livro;
import com.desenvolvimento.domains.dtos.AutorDTO;
import com.desenvolvimento.domains.dtos.EditoraDTO;
import com.desenvolvimento.repositories.EditoraRepository;
import com.desenvolvimento.services.exceptions.DataIntegrityViolationException;
import com.desenvolvimento.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepo;

    public List<EditoraDTO> findAll(){
        //retorna uma lista de EditoraDTO
        return editoraRepo.findAll().stream().map(obj -> new EditoraDTO(obj)).
                collect(Collectors.toList());

    }
    public Editora findbyId (int id){
        Optional<Editora> obj = editoraRepo.findById(id);
                return obj.orElseThrow(()-> new ObjectNotFoundException("Editora não encontrada! Id: " + id));
    }
    public Editora findbyCnpj(String cnpj){
        Optional<Editora> obj = editoraRepo.findByCnpj(cnpj);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Editora não encontrada! CNPJ: " + cnpj));
    }

    public Editora create(EditoraDTO dto){
        dto.setId(null);
        Editora obj = new Editora(dto);
        return editoraRepo.save(obj);
    }

    public Editora update(Integer id, EditoraDTO objDto){
        objDto.setId(id);
        Editora oldObj = findbyId(id);
        oldObj = new Editora(objDto);
        return editoraRepo.save(oldObj);
    }

    public void delete(Integer id){
        Editora obj = findbyId(id);
        if (obj.getLivros().size()>0){
            throw new DataIntegrityViolationException("Editora não pode ser deletado, pois tem um livro vinculado ");
        }
        editoraRepo.deleteById(id);
    }




}

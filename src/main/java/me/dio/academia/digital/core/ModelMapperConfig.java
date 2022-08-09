package me.dio.academia.digital.core;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var model = new ModelMapper();
        model.createTypeMap(AvaliacaoFisicaForm.class, AvaliacaoFisica.class)
                .addMapping(src -> src.getAlunoId(), (dest, value) -> dest.getAluno().setId((Long)value));
        return model;
    }
}

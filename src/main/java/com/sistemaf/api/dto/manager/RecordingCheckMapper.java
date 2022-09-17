package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.RecordingCheckInput;
import com.sistemaf.api.dto.model.RecordingCheckModel;
import com.sistemaf.api.dto.model.resume.RecordingCheckResumeModel;
import com.sistemaf.domain.model.VerificacaoGravacao;
import com.sistemaf.domain.projection.ResumoVerificacaoGravacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecordingCheckMapper {

  RecordingCheckMapper INSTANCE = Mappers.getMapper(RecordingCheckMapper.class);

  List<RecordingCheckResumeModel> toCollectionResumeModel(List<ResumoVerificacaoGravacao> content);


  List<RecordingCheckModel> toCollectionModel(List<VerificacaoGravacao> content);

  RecordingCheckModel toDto( VerificacaoGravacao produto);

  RecordingCheckResumeModel toResumeDto(ResumoVerificacaoGravacao resumoVerificacaoGravacao);

  VerificacaoGravacao toModel(RecordingCheckInput inputBody);

}

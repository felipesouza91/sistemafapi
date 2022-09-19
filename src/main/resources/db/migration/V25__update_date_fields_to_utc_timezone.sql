UPDATE
	fechamento_os
		SET data_fechamento = CONVERT_TZ(data_fechamento, '-03:00', '+00:00'),
			data_visita = CONVERT_TZ(data_visita, '-03:00', '+00:00');

UPDATE
	ordem_servico
		SET data_abertura  = CONVERT_TZ(data_abertura, '-03:00', '+00:00');


UPDATE
	verificacao_gravacoes
		SET data_teste = CONVERT_TZ(data_teste, '-03:00', '+00:00');
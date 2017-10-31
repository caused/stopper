/************************************************************************** 
* Verity TI
* --------------------------- 
* Criado por...:           Igor Cunha
* Em...........:           31/10/2017
* Projeto......:           PAUSE
* Descri��o....:           Script para preenchimento das tabelas do banco PAUSE
* Tabelas envoldidas:      Tabela de dominio
**************************************************************************/ 

DECLARE @nomeScript VARCHAR(MAX);
SET @nomeScript = '01. nome do script aqui'
PRINT 'Iniciando execu��o script ['+ @nomeScript +']'
BEGIN TRY
    BEGIN TRANSACTION;
		USE [Pause]

		INSERT INTO PAUSETipoAfastamento values ('F�rias'),
												('Licen�a n�o remunerada'),
												('Abono do Cliente'),
												('Novo Funcionario'),
												('Desligamento'),
												('Estagiario'),
												('Greve'),
												('QA360'),
												('Afastamento/INSS'),
												('Abono (Casamento e outro)');

		INSERT INTO PAUSETipoAtestado values ('Consulta m�dica'),
												('Consulta odontol�gica'),
												('Acompanhamento de consulta'),
												('Urg�ncia odontol�gica'),
												('Exames');

		INSERT INTO PAUSETipoJustificativa values ('Regulariza��o de registros'), ('Trabalho externo');

		COMMIT TRANSACTION;
 
    PRINT 'Sucesso na execu��o do script ['+ @nomeScript +']'
END TRY
BEGIN CATCH
		IF @@TRANCOUNT > 0
			 ROLLBACK TRANSACTION;
             
 		DECLARE @errorNumber INT;
		SET @errorNumber  = ERROR_NUMBER();
		DECLARE @errorLine INT;
		SET @errorLine = ERROR_LINE();
		DECLARE @errorMessage NVARCHAR(4000);
		SET @errorMessage = ERROR_MESSAGE();
		DECLARE @errorSeverity INT;
		SET @errorSeverity = ERROR_SEVERITY();
		DECLARE @errorState INT;
		SET @errorState = ERROR_STATE();
      
		PRINT 'ERRO na execu��o do script. [' + @nomeScript + ']'
		PRINT 'N�mero do erro: [' + CAST(@errorNumber AS VARCHAR(10)) + ']';
		PRINT 'N�mero da linha: [' + CAST(@errorLine AS VARCHAR(10)) + ']';
		PRINT 'Descri��o do erro: [' + @errorMessage + ']';
 
		RAISERROR(@errorMessage, @errorSeverity, @errorState);
END CATCH
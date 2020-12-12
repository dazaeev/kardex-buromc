CREATE DEFINER=`root`@`localhost` PROCEDURE `test`.`get_table`(in_schema varchar(50), in_search varchar(50), in_file varchar(50))
    READS SQL DATA
begin
    /* ---------------------- DESCRIPCION ----------------------------------------------||
	|| Busqueda avanzada                                                                ||
	|| Folio:                                                                           ||
	|| Nombre del proyecto:  BeITalent                                                  ||
	|| ---------------------------------------------------------------------------------||
	|| Realizado por: Ing. N. Dazaeev Gonzalez Herrera                                  ||
	|| Fabrica: BUROMC                                                                  ||
	|| Fecha: Julio 2019                                                                ||
	*/
	/* ---------------------- MODIFICACIONES ---------------------------------------------
	|| Buscar registros completos                                                       ||
	*/
	/* ------------------------- OBJETOS -------------------------------------------------
	|| ADMIN - temp_details                                                             ||
	*/
	/* ---------------------- VERSION 1.0 ------------------------------------------------
	|| Inicial                                                                          ||
	*/
	/* -----------------------------------------------------------------------------------
	|| Global declaration                                                               ||
	*/
	declare trunc_cmd varchar(50);
	declare search_string varchar(250);
	declare db, tbl, clmn char(50);
	declare done int default 0;
	declare counter int;
	/* -----------------------------------------------------------------------------------
	|| Cursor's                                                                         ||
	*/
	declare table_cur cursor for 
		select
			concat('select count(*) into @cnt_value from `',
			table_schema,
			'`.`',
			table_name,
			'` where `',
			column_name,
			'` regexp "',
			in_search,
			'"') ,
			table_schema,
			table_name,
			column_name
		from 
			information_schema.columns
		where
			table_schema in (in_schema);
	declare continue handler for 
		not found set done = 1;
	/* -----------------------------------------------------------------------------------
	|| Truncating table for refill the data for new search.                             ||
	*/
	prepare trunc_cmd from "truncate table temp_details;";
	execute trunc_cmd ;
	/* -----------------------------------------------------------------------------------
	|| Recorod cursor                                                                   ||
	*/
	open table_cur;
		table_loop:loop fetch table_cur into
			search_string,
			db,
			tbl,
			clmn;
			/* ---------------------------------------------------------------------------
			|| Executing the search                                                     ||
			*/
			set @search_string = search_string;
			/* ---------------------------------------------------------------------------
			|| Select search_string                                                     ||
			*/
			prepare search_string from @search_string;
			execute search_string;
			#
			set counter = @cnt_value;
			if counter>0 then
				/* -----------------------------------------------------------------------
				|| Inserting required results from search to table                      ||
				*/
				select rand() into @index_rand;
				insert into temp_details 
					values(
						db, 
						tbl, 
						clmn, 
						concat(
							in_file,
							tbl,
							'__',
							replace(@index_rand, '.', ''),
							'_',
							counter,
							'.csv')
					);
				/* -----------------------------------------------------------------------
				|| ON - create file temp                                                ||
				*/
				select group_concat(concat("\"",column_name,"\"")) into @search_column_a
					from information_schema.columns
				where table_name = cast(tbl as char character set utf8)
					and table_schema = in_schema
				order by ordinal_position;
				#
				select group_concat(column_name) into @search_column_b
					from information_schema.columns
				where table_name = cast(tbl as char character set utf8)
					and table_schema = in_schema
				order by ordinal_position;
				#
				set @call_sql = concat(
					'select ', @search_column_a, ' union all ',
					'select ', @search_column_b, ' from ',
					tbl,
					' where ',
					clmn,
					' regexp \'',
					in_search,
					'\' '
					'into outfile ', 
					'\'', 
					in_file,
					tbl,
					'__',
					replace(@index_rand, '.', ''),
					'_',
					counter,
					'.csv\'',
					' character set utf8', 
					' fields terminated by \',\' optionally enclosed by \'"\' lines terminated by \'\n\';'
					);
				prepare my_query from @call_sql;
				execute my_query;
				deallocate prepare my_query;
				/* -----------------------------------------------------------------------
				|| OFF - create file temp                                               ||
				*/
			end if;
			if done = 1 then 
				leave table_loop;
			end if;
		end loop;
	close table_cur;
	/* -----------------------------------------------------------------------------------
	|| Finally show results                                                             ||
	*/
	select concat(t.t_table, '|', t.t_field, '|', t.t_file) from temp_details t;
end;
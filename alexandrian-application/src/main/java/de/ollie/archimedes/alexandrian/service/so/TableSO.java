package de.ollie.archimedes.alexandrian.service.so;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * A container for table service objects.
 *
 * @author ollie
 *
 */
@Generated
@Accessors(chain = true)
@Data
@NoArgsConstructor
public class TableSO {

	@NonNull
	private String name;
	@NonNull
	private List<ColumnSO> columns = new ArrayList<>();
	@NonNull
	private List<ForeignKeySO> foreignKeys = new ArrayList<>();
	private TableMetaInfo metaInfo;
	private TableGUIInfo guiInfo;

}
package no.ntnu.fp.g20.model;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * Calendar model class.
 * @author Kristian Klomsten Skordal
 */
public class Calendar extends AbstractTableModel
{
	/**
	 * Constructs a new calendar model class.
	 */
	public Calendar()
	{
	}

	/**
	 * Gets the number of columns in the table.
	 * @return 7, the number of days in a week.
	 */
	public int getColumnCount()
	{
		return 7;
	}

	/**
	 * Gets the names of the columns in the table.
	 * @return the name of the day specified (zero based).
	 */
	@Override public String getColumnName(int day)
	{
		String[] days = {
			"Monday",
			"Tuesday",
			"Wednesday",
			"Thursday",
			"Friday",
			"Saturday",
			"Sunday"
		};

		return days[day];
	}

	/**
	 * Gets the number of rows in the table.
	 * This may be changed when it is decided how many hours to show.
	 * @return the number of rows in the table.
	 */
	public int getRowCount()
	{
		return 12;
	}

	/**
	 * Gets the object used for representing the contents of a cell.
	 * @param row the row to retrieve the contents of.
	 * @param col the column to retrieve the contents of.
	 * @return the contents of the specified cell.
	 */
	public Object getValueAt(int row, int col)
	{
		return null;
	}
}


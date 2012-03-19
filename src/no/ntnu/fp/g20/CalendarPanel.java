package no.ntnu.fp.g20;

import no.ntnu.fp.g20.model.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Panel containing the calendar view.
 * @author Kristian Klomsten Skordal
 */
public class CalendarPanel extends JPanel
	implements ComponentListener, PropertyChangeListener
{
	private JTable calendarTable;
	private Calendar model;

	/**
	 * Constructs a calendar view using the specified model.
	 * @param model the model to associate with this calendar view.
	 */
	public CalendarPanel(Calendar model)
	{
		this.model = model;
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(layout);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		calendarTable = new JTable(model);
		calendarTable.setFillsViewportHeight(true);
		calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		calendarTable.addComponentListener(this);

		layout.setConstraints(calendarTable.getTableHeader(), c);
		add(calendarTable.getTableHeader());

		c.gridy++;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		layout.setConstraints(calendarTable, c);
		add(calendarTable);
	}

	/**
	 * Function called when a property changes in one of the associated model classes.
	 * @param event the property change event.
	 */
	public void propertyChange(PropertyChangeEvent event)
	{

	}

	/**
	 * Function called when a component is hidden.
	 */
	public void componentHidden(ComponentEvent event)
	{

	}

	/**
	 * Function called when a component is moved.
	 */
	public void componentMoved(ComponentEvent event)
	{

	}

	/**
	 * Function called when a component is resized.
	 */
	public void componentResized(ComponentEvent event)
	{
		if(event.getSource() == calendarTable)
			calendarTable.setRowHeight(calendarTable.getHeight() / calendarTable.getRowCount());
	}

	/**
	 * Function called when a component is shown.
	 */
	public void componentShown(ComponentEvent event)
	{

	}
}


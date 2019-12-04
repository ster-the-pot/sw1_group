package edu.baylor.ecs.sw1.utils;

import java.awt.Component;
import java.util.Date;
import java.text.DateFormat;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Used in showing dates in the DatePicker portion of the EventEditDialog
 * @author Par Wilkinson
 *
 */

public class DateFormattedListCellRenderer extends DefaultListCellRenderer {
	private DateFormat format;
	
	public DateFormattedListCellRenderer(DateFormat format) {
		this.format = format;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		if(value instanceof Date) {
			value = format.format((Date) value);
		}
		
		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
}

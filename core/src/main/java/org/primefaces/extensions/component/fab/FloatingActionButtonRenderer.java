/*
 * Copyright 2011-2020 PrimeFaces Extensions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.extensions.component.fab;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.extensions.util.Attrs;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.util.WidgetBuilder;

/**
 * Renderer for the {@link FloatingActionButton} component.
 *
 * @author Jasper de Vries &lt;jepsar@gmail.com&gt;
 * @since 7.0.1
 */
public class FloatingActionButtonRenderer extends BaseMenuRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        decodeBehaviors(context, component);
    }

    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu menu) throws IOException {
        final FloatingActionButton fab = (FloatingActionButton) menu;
        final ResponseWriter writer = context.getResponseWriter();
        final String clientId = fab.getClientId(context);
        final String styleClass = getStyleClassBuilder(context)
                    .add(FloatingActionButton.STYLE_CLASS)
                    .add(fab.getStyleClass())
                    .build();

        writer.startElement("span", fab);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute(Attrs.CLASS, styleClass, "styleClass");
        encodeMainButton(writer, fab);
        encodeMenu(context, writer, fab);
        writer.endElement("span");
    }

    protected void encodeMainButton(ResponseWriter writer, FloatingActionButton fab) throws IOException {
        // Button, start
        writer.startElement("span", fab);
        String classes = "ui-fab-main ui-button";
        if (fab.getIconActive() != null) {
            classes += " ui-fab-double";
        }
        writer.writeAttribute(Attrs.CLASS, classes, "styleClass");
        if (fab.getStyle() != null) {
            writer.writeAttribute(Attrs.STYLE, fab.getStyle(), Attrs.STYLE);
        }

        // Icon
        writer.startElement("span", fab);
        writer.writeAttribute(Attrs.CLASS, "ui-icon ui-icon-0 " + fab.getIcon(), "icon");
        writer.endElement("span");
        if (fab.getIconActive() != null) {
            writer.startElement("span", fab);
            writer.writeAttribute(Attrs.CLASS, "ui-icon ui-icon-1 " + fab.getIconActive(), "icon");
            writer.endElement("span");
        }

        // Button, end
        writer.endElement("span");
    }

    protected void encodeMenu(FacesContext context, ResponseWriter writer, FloatingActionButton fab) throws IOException {
        writer.startElement("ul", fab);
        for (final MenuElement child : (List<MenuElement>) fab.getElements()) {
            if (child.isRendered() && child instanceof MenuItem) {
                encodeMenuItem(context, writer, fab, (MenuItem) child);
            }
        }
        writer.endElement("ul");
    }

    protected void encodeMenuItem(FacesContext context, ResponseWriter writer, FloatingActionButton fab, MenuItem menuItem) throws IOException {
        writer.startElement("li", fab);
        writer.writeAttribute("role", "menuitem", null);
        writer.writeAttribute(Attrs.CLASS, "ui-button", null);
        // Use style here allowing to set background color
        if (menuItem.getStyle() != null) {
            writer.writeAttribute(Attrs.STYLE, menuItem.getStyle(), null);
            menuItem.setStyleClass(null);
        }
        if (!menuItem.isDisabled()) {
            encodeMenuItem(context, fab, menuItem, fab.getTabindex());
        }
        writer.endElement("li");
    }

    @Override
    protected void encodeScript(FacesContext context, AbstractMenu menu) throws IOException {
        final FloatingActionButton fab = (FloatingActionButton) menu;
        final WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("ExtFAB", fab);
        if (fab.isKeepOpen()) {
            wb.attr("keepOpen", fab.isKeepOpen());
        }
        encodeClientBehaviors(context, fab);
        wb.finish();
    }

}

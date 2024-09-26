/*
 * Copyright (c) 2011-2024 PrimeFaces Extensions
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.primefaces.extensions.showcase.controller.osmap;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@RequestScoped
public class MarkersView implements Serializable {

    private static final long serialVersionUID = 1L;

    private MapModel<Long> simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel<>();

        simpleModel.addOverlay(new Marker<>(new LatLng(36.879466, 30.667648), "Konyaalti", 1L));
        simpleModel.addOverlay(new Marker<>(new LatLng(36.883707, 30.689216), "Ataturk Parki", 2L));

        Marker marker3 = new Marker<>(new LatLng(36.879703, 30.706707), "Karaalioglu Parki", 3L,
                    "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png");
        simpleModel.addOverlay(marker3);

        Marker marker4 = new Marker<>(new LatLng(36.885233, 30.702323), "Kaleici", 4L);
        simpleModel.addOverlay(marker4);
    }

    public MapModel<Long> getSimpleModel() {
        return simpleModel;
    }
}
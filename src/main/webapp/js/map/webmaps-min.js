function baiduNavigation(j, c, f, n, m, o) {
    function d(v, w) {
        for (var t = 0; t < w.length; t++) {
            var u = w[t];
            if (v.lng == u.lng && v.lat == u.lat) {
                return t
            }
        }
        for (var t = 0; t < w.length; t++) {
            var u = w[t];
            var s = Math.abs(v.lng - u.lng);
            var r = Math.abs(v.lat - u.lat);
            if (s <= 0.000005 && r <= 0.000005) {
                return t
            }
        }
        return - 1
    }
    var q = {
        policy: m,
        onSearchComplete: function(A) {
            var C = g.getStatus();
            if (C == BMAP_STATUS_SUCCESS) {
                var t = A.getPlan(0);
                var v = t.getDistance(false);
                var y = t.getDuration(false);
                var I = t.getRoute(0);
                var z = I.getPath();
                var F = [];
                var G = [];
                for (var E = 0; E < I.getNumSteps(); E++) {
                    var K = I.getStep(E);
                    F.push(K.getDescription(false));
                    var w = K.getPosition();
                    var x = d(w, z);
                    if (x == -1) {
                        if (E == I.getNumSteps() - 1) {
                            G.push(z.length - 1)
                        } else {
                            G.push(x)
                        }
                    } else {
                        G.push(x)
                    }
                }
                var D = [];
                for (var E = 0; E < z.length; E++) {
                    var H = z[E];
                    var J = H.lng;
                    var u = H.lat;
                    var s = BaiduConverter.decrypt(J, u);
                    var r = Deconverter.decode(s.x, s.y);
                    var B = {
                        lon: r.x,
                        lat: r.y
                    };
//                    console.log(B.lon + " " + B.lat);
                    D.push(B)
                }
                o(true, v, y, D, F, G)
            } else {
                o(false, C)
            }
        }
    };
    var b = new Converter();
    var p = b.getEncryPoint(parseFloat(j), parseFloat(c));
    var e = BaiduConverter.encrypt(p.x, p.y);
    var l = new BMap.Point(e.x, e.y);
    var i = new Converter();
    var h = i.getEncryPoint(parseFloat(f), parseFloat(n));
    var k = BaiduConverter.encrypt(h.x, h.y);
    var a = new BMap.Point(k.x, k.y);
    var g = new BMap.DrivingRoute("中国", q);
    g.search(l, a)
}
var BaiduMapProxy = function(k, m) {
    var ak = this;
    var F = m;
    var K = new BMap.Map(k, {enableMapClick:false});
    var aq = [];
    var G = [];
    var y = [];
    var E = [aq, G];
    this.getOriMap = function(){return K};
    this.init = function() {
        K.addControl(new BMap.NavigationControl());
        K.addControl(new BMap.ScaleControl());
        K.addControl(new BMap.OverviewMapControl());
        K.addControl(new BMap.MapTypeControl({
            type: BMAP_MAPTYPE_CONTROL_HORIZONTAL,
            mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP]
        }));
        K.enableScrollWheelZoom();
        K.enableAutoResize();
        var au = new BMap.PanoramaControl();
        au.setOffset(new BMap.Size(10, 38));
        K.addControl(au);
        au.setAnchor(BMAP_ANCHOR_TOP_RIGHT);
        var at = new BMapLib.TrafficControl({
            showPanel: false
        });
        at.setOffset(new BMap.Size(92, 10));
        K.addControl(at);
        at.setAnchor(BMAP_ANCHOR_TOP_RIGHT);
        a();
        R()
    };
    var N = null;
    function c(at) {
        if (N != null) {
            return
        }
        var au = {
            whiteSpace: "nowrap",
            padding: "5px 2px 2px 5px",
            margin: "-20px 0 0 -6px",
            cursor: "pointer",
            border: "1px solid #6482B9"
        };
        if (isIE) {
            au.filter = "alpha(opacity=80)"
        } else {
            au.opacity = "0.8"
        }
        N = new ab(4, at, "删除标注", null, au);
        K.addOverlay(N);
        N.triggerMarker = null;
        af();
        N.div.onclick = function() {
            F.deleteStaticMarker(N.triggerMarker);
            N.hide()
        }
    }
    var b = function() {
        if (N != null) {
            N.hide()
        }
    };
    function af() {
        if (document.addEventListener) {
            document.addEventListener("click", b, false)
        } else {
            if (document.attachEvent) {
                document.attachEvent("onclick", b)
            }
        }
    }
    function ac() {
        if (document.addEventListener) {
            document.removeEventListener("click", b, false)
        } else {
            if (document.attachEvent) {
                document.detachEvent("onclick", b)
            }
        }
    }
    this.destroyMap = function() {
        H();
        ac()
    };
    this.getCenter = function() {
        var at = K.getCenter();
        var aw = at.lng;
        var au = at.lat;
        var ax = BaiduConverter.decrypt(aw, au);
        var av = Deconverter.decode(ax.x, ax.y);
        return {
            lon: av.x,
            lat: av.y
        }
    };
    this.isPointInView = function(ax, av) {
        var ay = new Converter();
        var aw = ay.getEncryPoint(parseFloat(ax), parseFloat(av));
        var au = BaiduConverter.encrypt(aw.x, aw.y);
        var at = new BMap.Point(au.x, au.y);
        return K.getBounds().containsPoint(at)
    };
    this.getZoom = function() {
        return K.getZoom()
    };
    this.setCenter = function(ax, av) {
        var ay = new Converter();
        var aw = ay.getEncryPoint(parseFloat(ax), parseFloat(av));
        var au = BaiduConverter.encrypt(aw.x, aw.y);
        var at = new BMap.Point(au.x, au.y);
        K.setCenter(at)
    };
    this.centerAndZoom = function(ax, av, az) {
        var ay = new Converter();
        var aw = ay.getEncryPoint(parseFloat(ax), parseFloat(av));
        var au = BaiduConverter.encrypt(aw.x, aw.y);
        if(au.x<0 || au.y<0){
            var c=new Convertor();
            var r1=c.WGS2BD09({lng:ax,lat:av});
            au.x = r1.lng;
            au.y = r1.lat;
        }
        var at = new BMap.Point(au.x, au.y);
        K.centerAndZoom(at, az)
    };
    this.fitBounds = function(aC, az, aB, ax) {
        var aw = new Converter();
        var au = aw.getEncryPoint(parseFloat(aC), parseFloat(az));
        var aA = BaiduConverter.encrypt(au.x, au.y);
        var av = new Converter();
        var at = av.getEncryPoint(parseFloat(aB), parseFloat(ax));
        var ay = BaiduConverter.encrypt(at.x, at.y);
        var aF = new BMap.Point(aA.x, aA.y);
        var aE = new BMap.Point(ay.x, ay.y);
        var aD = K.getViewport([aF, aE]);
        K.setViewport(aD, {
            enableAnimation: false
        })
    };
    this.openDistanceTool = function() {
        O("rule")
    };
    this.openCoordTool = function() {
        O("coord")
    };
    this.drawPoint = function(at) {
        W = at;
        O("point")
    };
    this.drawCircle = function(at) {
        W = at;
        O("circle")
    };
    this.drawRectangle = function(at) {
        W = at;
        O("rect")
    };
    this.drawPolygon = function(at) {
        W = at;
        O("polygon")
    };
    this.drawPolyline = function(at) {
        W = at;
        O("polyline")
    };
    this.resize = function() {};
    this.newSimpleMarker = function(az, ax, aw, ay, aA) {
        var au = new SEGSimpleMarker();
        var at = new f(az, ax, aw, ay);
        au.target = at;
        if (typeof(az) == "object") {
            var av = az;
            au.config = av;
            au.lon = av.lon;
            au.lat = av.lat;
            au.label = av.label;
            au.title = av.title;
            au.id = av.id
        } else {
            au.lon = az;
            au.lat = ax;
            au.label = aw;
            au.title = ay;
            au.id = aA
        }
        return au
    };
    this.setSimpleMarkerIcon = function(aE, au) {
        if (!this.isMarkerOnMap(aE)) {
            return
        }
        var av = au.url;
        var aw = au.width;
        var aD = au.height;
        var ay = au.left || 0;
        var aC = au.top || 0;
        var aB = (typeof(au.anchorx) == "undefined") ? -(aw / 2) : (au.anchorx);
        var aA = (typeof(au.anchory) == "undefined") ? -aD: (au.anchory);
        var az = (typeof(au.winx) == "undefined") ? 0 : (au.winx);
        var ax = (typeof(au.winy) == "undefined") ? -aD + 1 : (au.winy);
        var at = aE.target.iconDiv;
        at.style.width = aw + "px";
        at.style.height = aD + "px";
        at.style.background = "url(" + av + ") " + ay + "px " + aC + "px no-repeat";
        at.style.left = aB + "px";
        at.style.top = aA + "px";
        aE.target.winx = az;
        aE.target.winy = ax
    };
    this.toTop = function(at, au) {
        at.target.setZIndex(au ? 1 : 0)
    };
    var S = [];
    this.newInfoWindow = function(aE, aD, aw, aF, av) {
        aw -= 35;
        aF -= 30;
        var az = 0;
        var ay = -32;
        if (typeof(av) != "undefined") {
            if (typeof(av.offsetX) != "undefined") {
                az = av.offsetX
            }
            if (typeof(av.offsetY) != "undefined") {
                ay = av.offsetY
            }
        }
        var at = {
            offset: new BMap.Size(az, ay),
            width: aw,
            height: aF,
            enableMessage: false
        };
        var aC;
        var ax = null;
        if (typeof(aD) == "object") {
            var au = new SEGUtil.Div(0, 0, null, null).get();
            var aB = 35;
            if (typeof(aE) == "object") {
                ax = aE
            } else {
                ax = new SEGUtil.Div(0, 0, aw, aB).get();
                ax.style.padding = "2px 5px";
                ax.style.borderBottom = "1px solid #ccc";
                ax.style.fontWeight = "bolder";
                ax.innerHTML = aE
            }
            au.appendChild(ax);
            aD.style.top = aB + "px";
            au.appendChild(aD);
            aC = new BMap.InfoWindow(au, at)
        } else {
            aC = new BMap.InfoWindow(aD, at);
            aC.setTitle(aE)
        }
        var aA = new SEGInfoWindow();
        aA.target = aC;
        aA.titleDiv = ax;
        S.push(aA);
        return aA
    };
    this.showInfoWindow = function(au, at) {
        K.openInfoWindow(at.target, au.target.point)
    };
    this.setInfoWindowTitle = function(at, au) {
        if (at.titleDiv != null) {
            at.titleDiv.innerHTML = au
        } else {
            at.target.setTitle(au)
        }
    };
    this.isInfoWindowExist = function(au) {
        var at = SEGUtil.indexOfArray(S, au);
        return at != -1
    };
    this.closeInfoWindow = function(at) {
        if (!this.isInfoWindowExist(at)) {
            return
        }
        at.target.close()
    };
    this.closeAllInfoWindow = function() {
        for (var at = 0; at < S.length; at++) {
            S[at].target.close()
        }
    };
    this.addEventListener = function(av, at, au) {
        if (SEGSimpleMarker.prototype.isPrototypeOf(av)) {
            SEGUtil.addEventForDom(av.target.div, at, au)
        }
    };
    this.newVehicleMarker = function(av) {
        var at = new am(av);
        var au = new SEGVehicleMarker();
        au.target = at;
        au.opts = av;
        return au
    };
    this.newCircle = function(aw, aA, ax, aD, aE, aJ, av, ay, aG) {
        var aK = new Converter();
        var au = aK.getEncryPoint(parseFloat(aw), parseFloat(aA));
        var at = BaiduConverter.encrypt(au.x, au.y);
        var aF = new BMap.Point(at.x, at.y);
        var aC = typeof(aJ) == "undefined" ? 2 : aJ;
        var aI = typeof(av) == "undefined" ? 1 : av;
        var aB = typeof(aG) == "undefined" ? 0.65 : aG;
        var az = new BMap.Circle(aF, ax, {
            enableClicking: false,
            enableEditing: false,
            strokeColor: aE || "blue",
            strokeWeight: aC,
            strokeOpacity: aI,
            fillColor: ay || "white",
            fillOpacity: aB
        });
        var aH = new SEGCircle();
        aH.target = az;
        aH.lon = aw;
        aH.lat = aA;
        aH.radius = ax;
        aH.id = aD;
        aH.strokeColor = aE;
        aH.strokeWeight = aJ;
        aH.strokeOpacity = av;
        aH.fillColor = ay;
        aH.fillOpacity = aG;
        return aH
    };
    this.newRectangle = function(aN, av, aM, at, aC, aF, aK, au, aw, aI) {
        var aE = new Converter();
        var aH = aE.getEncryPoint(parseFloat(aN), parseFloat(av));
        var ay = BaiduConverter.encrypt(aH.x, aH.y);
        var aD = new Converter();
        var aG = aD.getEncryPoint(parseFloat(aM), parseFloat(at));
        var ax = BaiduConverter.encrypt(aG.x, aG.y);
        var aB = typeof(aK) == "undefined" ? 2 : aK;
        var aJ = typeof(au) == "undefined" ? 1 : au;
        var az = typeof(aI) == "undefined" ? 0.65 : aI;
        var aA = new BMap.Polygon(d(ay.x, ay.y, ax.x, ax.y), {
            enableEditing: false,
            enableClicking: false,
            strokeColor: aF || "blue",
            strokeWeight: aB,
            strokeOpacity: aJ,
            fillColor: aw || "white",
            fillOpacity: az
        });
        var aL = new SEGRectangle();
        aL.target = aA;
        aL.lon1 = aN;
        aL.lat1 = av;
        aL.lon2 = aM;
        aL.lat2 = at;
        aL.id = aC;
        aL.strokeColor = aF;
        aL.strokeWeight = aK;
        aL.strokeOpacity = au;
        aL.fillColor = aw;
        aL.fillOpacity = aI;
        return aL
    };
    this.newPolygon = function(aE, aC, aD, aJ, aw, ax, aG) {
        var aA = [];
        for (var aH = 0; aH < aE.length; aH++) {
            var aK = aE[aH];
            var aL = new Converter();
            var au = aL.getEncryPoint(parseFloat(aK.lon), parseFloat(aK.lat));
            var at = BaiduConverter.encrypt(au.x, au.y);
            var aF = new BMap.Point(at.x, at.y);
            aA.push(aF)
        }
        var aB = typeof(aJ) == "undefined" ? 2 : aJ;
        var aI = typeof(aw) == "undefined" ? 1 : aw;
        var ay = typeof(aG) == "undefined" ? 0.65 : aG;
        var az = new BMap.Polygon(aA, {
            enableEditing: false,
            enableClicking: false,
            strokeColor: aD || "blue",
            strokeWeight: aB,
            strokeOpacity: aI,
            fillColor: ax || "white",
            fillOpacity: ay
        });
        var av = new SEGPolygon();
        av.target = az;
        av.ps = aE;
        av.id = aC;
        av.strokeColor = aD;
        av.strokeWeight = aJ;
        av.strokeOpacity = aw;
        av.fillColor = ax;
        av.fillOpacity = aG;
        return av
    };
    this.newPolyline = function(at, au, aI, ax, az) {
        var aH = [];
        for (var aw = 0; aw < at.length; aw++) {
            var aC = at[aw];
            var aA = new Converter();
            var aG = aA.getEncryPoint(parseFloat(aC.lon), parseFloat(aC.lat));
            var aF = BaiduConverter.encrypt(aG.x, aG.y);
            var aE = new BMap.Point(aF.x, aF.y);
            aH.push(aE)
        }
        var aD = typeof(ax) == "undefined" ? 2 : ax;
        var av = typeof(az) == "undefined" ? 1 : az;
        var aB = new BMap.Polyline(aH, {
            enableEditing: false,
            enableClicking: false,
            strokeColor: aI || "blue",
            strokeWeight: aD,
            strokeOpacity: av
        });
        var ay = new SEGPolyline();
        ay.target = aB;
        ay.ps = at;
        ay.id = au;
        ay.strokeColor = aI;
        ay.strokeWeight = ax;
        ay.strokeOpacity = az;
        return ay
    };
    this.addMarker = function(at, au) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(at)) {
            K.addOverlay(at.target);
            y.push(at);
            return
        }
        var av = null;
        if (typeof(au) == "undefined" || au == 0) {
            av = aq
        } else {
            if (au == 1) {
                av = G
            }
        }
        if (av) {
            K.addOverlay(at.target);
            av.push(at)
        }
        if (typeof(au) != "undefined" && au == 1 && SEGSimpleMarker.prototype.isPrototypeOf(at)) {
            SEGUtil.addEventForDom(at.target.iconDiv, "contextmenu",
            function() {
                if (N == null) {
                    c(at.target.point)
                } else {
                    N.updatePosition(at.target.point);
                    N.show()
                }
                N.triggerMarker = at
            })
        }
    };
    this.copyMarker = function(at) {
        switch (at.markerType) {
        case 1:
            if (at.config) {
                return this.newSimpleMarker(at.config)
            }
            return this.newSimpleMarker(at.lon, at.lat, at.label, at.title, at.id);
        case 2:
            return this.newVehicleMarker(at.opts);
        case 3:
            return this.newCircle(at.lon, at.lat, at.radius, at.id, at.strokeColor, at.strokeWeight, at.strokeOpacity, at.fillColor, at.fillOpacity);
        case 4:
            return this.newRectangle(at.lon1, at.lat1, at.lon2, at.lat2, at.id, at.strokeColor, at.strokeWeight, at.strokeOpacity, at.fillColor, at.fillOpacity);
        case 5:
            return this.newPolygon(at.ps, at.id, at.strokeColor, at.strokeWeight, at.strokeOpacity, at.fillColor, at.fillOpacity);
        case 6:
            return this.newPolyline(at.ps, at.id, at.strokeColor, at.strokeWeight, at.strokeOpacity);
        default:
            return null
        }
    };
    this.isMarkerOnMap = function(at) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(at)) {
            for (var av = 0; av < y.length; av++) {
                if (y[av] == at) {
                    return true
                }
            }
        } else {
            for (var av = 0; av < E.length; av++) {
                var aw = E[av];
                for (var au = 0; au < aw.length; au++) {
                    if (aw[au] == at) {
                        return true
                    }
                }
            }
        }
        return false
    };
    function j(au) {
        for (var at = 0; at < y.length; at++) {
            if (y[at].opts.id == au) {
                return y[at]
            }
        }
        return null
    }
    this.addOrUpdateVehicleMarkerById = function(au) {
        var aw = au.id;
        var at = j(aw);
        if (at != null) {
            at.target.update(au);
            return at
        }
        var av = this.newVehicleMarker(au);
        this.addMarker(av);
        return av
    };
    this.removeMarker = function(at) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(at)) {
            for (var av = 0; av < y.length; av++) {
                if (y[av] == at) {
                    K.removeOverlay(at.target);
                    y.splice(av, 1);
                    return true
                }
            }
        } else {
            for (var av = 0; av < E.length; av++) {
                var aw = E[av];
                for (var au = 0; au < aw.length; au++) {
                    if (aw[au] == at) {
                        K.removeOverlay(at.target);
                        aw.splice(au, 1);
                        return true
                    }
                }
            }
        }
        return false
    };
    this.clearNonStaticMarkers = function() {
        for (var at = 0; at < aq.length; at++) {
            K.removeOverlay(aq[at].target)
        }
        aq.splice(0, aq.length)
    };
    this.clearStaticMarkers = function() {
        for (var at = 0; at < G.length; at++) {
            K.removeOverlay(G[at].target)
        }
        G.splice(0, G.length)
    };
    this.clearVehicleMarkers = function() {
        for (var at = 0; at < y.length; at++) {
            K.removeOverlay(y[at].target)
        }
        y.splice(0, y.length)
    };
    this.getNonStaticMarkers = function() {
        return aq
    };
    this.getStaticMarkers = function() {
        return G
    };
    this.getVehicleMarkers = function() {
        return y
    };
    var X = null;
    var W = null;
    var M = null;
    var D = null;
    var v = null;
    var e = null;
    var Z = null;
    function ap() {
        D = null;
        v = null;
        e = null;
        Z = null
    }
    function L(at) {
        for (var au = 0; au < at.length; au++) {
            K.removeOverlay(at[au])
        }
    }
    var I = null;
    var P = null;
    var B = null;
    function ae() {
        I = null;
        P = null;
        B = null
    }
    var al = null;
    var an = null;
    var Q = null;
    function A() {
        al = null;
        an = null;
        Q = null
    }
    var q = null;
    var ar = null;
    var g = null;
    function ad() {
        q = null;
        ar = null;
        g = null
    }
    var z = null;
    var p = null;
    var x = null;
    function s() {
        z = null;
        p = null;
        x = null
    }
    var n = null;
    var ai = null;
    function U() {
        ai = new Y();
        K.addOverlay(ai)
    }
    function H() {
        if (ai != null) {
            ai.clearEvents();
            K.removeOverlay(ai);
            ai = null
        }
    }
    function R() {
        var aw = {
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -6px",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.png) no-repeat -25px -312px"
        };
        var av = {
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -20px",
            cursor: "pointer",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.gif) no-repeat 0px -14px"
        };
        var ax = {
            whiteSpace: "nowrap",
            color: "#333333",
            height: "20px",
            border: "1px solid #333333",
            margin: "-6px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var ay = {
            whiteSpace: "nowrap",
            width: "120px",
            border: "1px solid red",
            margin: "20px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var au = {
            whiteSpace: "nowrap",
            width: "140px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        var at = {
            whiteSpace: "nowrap",
            width: "160px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        if (isIE) {
            ax.filter = "alpha(opacity=70)";
            ay.filter = "alpha(opacity=70)"
        } else {
            ax.opacity = "0.7";
            ay.opacity = "0.7"
        }
        K.addEventListener("click",
        function(aE) {
            var aI = aE.point;
            switch (X) {
            case "point":
                O("pan");
                if (n != null) {
                    K.removeOverlay(n);
                    n = null
                }
                if (W) {
                    var az = aI.lng;
                    var aG = aI.lat;
                    var aL = BaiduConverter.decrypt(az, aG);
                    var aK = Deconverter.decode(aL.x, aL.y);
                    var aA = Math.round(aK.x * 1000000) / 1000000;
                    var aH = Math.round(aK.y * 1000000) / 1000000;
                    W(aA, aH)
                }
                break;
            case "polygon":
                if (ar == null) {
                    var aM = [aI];
                    ar = new BMap.Polygon(aM, {
                        enableEditing: false,
                        enableClicking: false,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 1,
                        fillColor: "white",
                        fillOpacity: 0.65
                    });
                    K.addOverlay(ar)
                } else {
                    var aM = ar.getPath();
                    if (g != null) {
                        aM.pop();
                        g = null
                    }
                    aM.push(aI);
                    ar.setPath(aM)
                }
                break;
            case "polyline":
                if (p == null) {
                    var aM = [aI];
                    p = new BMap.Polyline(aM, {
                        enableEditing: false,
                        enableClicking: false,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 1
                    });
                    K.addOverlay(p)
                } else {
                    var aM = p.getPath();
                    if (x != null) {
                        aM.pop();
                        x = null
                    }
                    aM.push(aI);
                    p.setPath(aM)
                }
                break;
            case "rule":
                if (D == null) {
                    v = [];
                    var aC = {
                        enableClicking: false,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 0.6
                    };
                    D = new BMap.Polyline([aI], aC);
                    K.addOverlay(D);
                    var aJ = new ab(1, aI, $sp, null, ax);
                    K.addOverlay(aJ);
                    v.push(D);
                    v.push(aJ)
                } else {
                    var aF = D.getPath();
                    if (Z != null) {
                        aF.pop();
                        Z = null
                    }
                    aF.push(aI);
                    D.setPath(aF);
                    var aD = ah(D);
                    var aJ = new ab(1, aI, aD[0] + aD[1], null, ax);
                    K.addOverlay(aJ);
                    v.push(aJ)
                }
                var aB = new ab(1, aI, null, null, aw);
                K.addOverlay(aB);
                v.push(aB);
                break;
            default:
                break
            }
        });
        K.addEventListener("rightclick",
        function(aA) {
            switch (X) {
            case "polygon":
                if (ar != null) {
                    var az = ar.getPath();
                    if (g != null) {
                        az.pop();
                        g = null
                    }
                    az.pop();
                    ar.setPath(az)
                }
                break;
            case "polyline":
                if (p != null) {
                    var az = p.getPath();
                    if (x != null) {
                        az.pop();
                        x = null
                    }
                    az.pop();
                    p.setPath(az)
                }
                break;
            default:
                break
            }
        });
        K.addEventListener("mousemove",
        function(aD) {
            var aG = aD.point;
            switch (X) {
            case "point":
                var az = aG.lng;
                var aE = aG.lat;
                var aI = BaiduConverter.decrypt(az, aE);
                var aH = Deconverter.decode(aI.x, aI.y);
                var aA = Math.round(aH.x * 1000000) / 1000000;
                var aF = Math.round(aH.y * 1000000) / 1000000;
                var aB = "<span>" + aA + ", " + aF + "</span><div style='color:#808080'>" + $ctsp + "</div>";
                if (n == null) {
                    n = new ab(2, aG, aB, null, au);
                    K.addOverlay(n)
                } else {
                    n.updatePosition(aG);
                    n.updateContent(aB)
                }
                break;
            case "polygon":
                if (q == null) {
                    var aB = "<span>" + $ctspgp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    q = new ab(2, aG, aB, null, at);
                    K.addOverlay(q)
                } else {
                    q.updatePosition(aG)
                }
                if (ar != null) {
                    var aJ = ar.getPath();
                    if (g != null) {
                        aJ.pop();
                        g = null
                    }
                    g = aG;
                    aJ.push(g);
                    ar.setPath(aJ)
                }
                break;
            case "polyline":
                if (z == null) {
                    var aB = "<span>" + $ctsplp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    z = new ab(2, aG, aB, null, at);
                    K.addOverlay(z)
                } else {
                    z.updatePosition(aG)
                }
                if (p != null) {
                    var aJ = p.getPath();
                    if (x != null) {
                        aJ.pop();
                        x = null
                    }
                    x = aG;
                    aJ.push(x);
                    p.setPath(aJ)
                }
                break;
            case "rule":
                if (e == null) {
                    e = new ab(2, aG, $cts, null, ay);
                    K.addOverlay(e)
                } else {
                    e.updatePosition(aG)
                }
                if (D != null) {
                    var aJ = D.getPath();
                    if (Z != null) {
                        aJ.pop();
                        Z = null
                    }
                    Z = aG;
                    aJ.push(Z);
                    D.setPath(aJ);
                    var aC = ah(D);
                    var aB = $ttd + "：<span style='color:red'>" + aC[0] + "</span>" + aC[1] + "<div style='color:#333333'>" + $ca + "</div>";
                    e.updateContent(aB)
                }
                break;
            case "coord":
                var az = aG.lng;
                var aE = aG.lat;
                var aI = BaiduConverter.decrypt(az, aE);
                var aH = Deconverter.decode(aI.x, aI.y);
                var aA = Math.round(aH.x * 1000000) / 1000000;
                var aF = Math.round(aH.y * 1000000) / 1000000;
                var aB = "<span>" + aA + ", " + aF + "</span><div style='color:#808080'>" + $dcts + "</div>";
                if (M == null) {
                    M = new ab(2, aG, aB, null, au);
                    K.addOverlay(M)
                } else {
                    M.updatePosition(aG);
                    M.updateContent(aB)
                }
                break;
            default:
                break
            }
        });
        K.addEventListener("dblclick",
        function(aE) {
            switch (X) {
            case "rule":
                O("pan");
                var aG = aE.point;
                var aI = new ab(1, aG, null, null, av);
                K.addOverlay(aI);
                v.push(aI);
                var aL = v;
                aI.div.onclick = function() {
                    L(aL)
                };
                K.removeOverlay(e);
                ap();
                break;
            case "polygon":
                O("pan");
                K.removeOverlay(q);
                if (W && ar) {
                    var az = [];
                    var aM = ar.getPath();
                    for (var aC = 0; aC < aM.length; aC++) {
                        var aB = aM[aC];
                        var aK = BaiduConverter.decrypt(aB.lng, aB.lat);
                        var aH = Deconverter.decode(aK.x, aK.y);
                        var aA = Math.round(aH.x * 1000000) / 1000000;
                        var aF = Math.round(aH.y * 1000000) / 1000000;
                        az.push({
                            lon: aA,
                            lat: aF
                        })
                    }
                    var aJ = new SEGPolygon();
                    aJ.target = ar;
                    aJ.ps = az;
                    aJ.id = null;
                    aJ.strokeColor = "red";
                    aJ.strokeWeight = 2;
                    aJ.strokeOpacity = 1;
                    aJ.fillColor = "white";
                    aJ.fillOpacity = 0.65;
                    aq.push(aJ);
                    W(az, aJ)
                }
                ad();
                break;
            case "polyline":
                O("pan");
                K.removeOverlay(z);
                if (W && p) {
                    var az = [];
                    var aM = p.getPath();
                    for (var aC = 0; aC < aM.length; aC++) {
                        var aB = aM[aC];
                        var aK = BaiduConverter.decrypt(aB.lng, aB.lat);
                        var aH = Deconverter.decode(aK.x, aK.y);
                        var aA = Math.round(aH.x * 1000000) / 1000000;
                        var aF = Math.round(aH.y * 1000000) / 1000000;
                        az.push({
                            lon: aA,
                            lat: aF
                        })
                    }
                    var aD = new SEGPolyline();
                    aD.target = p;
                    aD.ps = az;
                    aD.id = null;
                    aD.strokeColor = "red";
                    aD.strokeWeight = 2;
                    aD.strokeOpacity = 1;
                    aq.push(aD);
                    W(az, aD)
                }
                s();
                break;
            case "coord":
                O("pan");
                if (M != null) {
                    K.removeOverlay(M);
                    M = null
                }
                break;
            default:
                break
            }
        })
    }
    var O = function(au, at) {
        X = au;
        switch (X) {
        case "pan":
            K.setDefaultCursor("url(" + SEGUtil.imageRootDir + "/openhand.cur) 8 8, default");
            K.setDraggingCursor("url(" + SEGUtil.imageRootDir + "/closedhand.cur) 8 8, move");
            K.enableDragging();
            setTimeout(function() {
                K.enableDoubleClickZoom()
            },
            200);
            break;
        case "point":
            K.setDefaultCursor("crosshair");
            K.setDraggingCursor("crosshair");
            break;
        case "circle":
            K.setDefaultCursor("crosshair");
            K.setDraggingCursor("crosshair");
            K.disableDragging();
            U();
            break;
        case "rect":
            K.setDefaultCursor("crosshair");
            K.setDraggingCursor("crosshair");
            K.disableDragging();
            U();
            break;
        case "polygon":
            K.setDefaultCursor("crosshair");
            K.setDraggingCursor("crosshair");
            K.disableDoubleClickZoom();
            break;
        case "polyline":
            K.setDefaultCursor("crosshair");
            K.setDraggingCursor("crosshair");
            K.disableDoubleClickZoom();
            break;
        case "rule":
            K.setDefaultCursor("url('" + SEGUtil.imageRootDir + "/ruler.cur'), auto");
            K.setDraggingCursor("url(" + SEGUtil.imageRootDir + "/ruler.cur), auto");
            K.disableDoubleClickZoom();
            break;
        case "coord":
            K.setDefaultCursor("crosshair");
            K.setDraggingCursor("crosshair");
            K.disableDoubleClickZoom();
            break;
        default:
            break
        }
    };
    function ab(az, at, ax, ay, av) {
        this.zIndex = az;
        this.point = at;
        this.content = ax;
        this.title = ay;
        this.style = av;
        this.div = new SEGUtil.Div(0, 0, null, null).get();
        if (this.content) {
            this.div.innerHTML = this.content
        }
        if (this.title) {
            this.div.title = this.title
        }
        this.div.style.backgroundColor = "white";
        if (this.style) {
            var aw = this.style;
            for (var au in aw) {
                this.div.style[au] = aw[au]
            }
        }
    }
    ab.prototype = new BMap.Overlay();
    ab.prototype.initialize = function(at) {
        this.map = at;
        switch (this.zIndex) {
        case 0:
            at.getPanes().mapPane.appendChild(this.div);
            break;
        case 1:
            at.getPanes().markerPane.appendChild(this.div);
            break;
        case 2:
            at.getPanes().labelPane.appendChild(this.div);
            break;
        case 3:
            at.getPanes().floatShadow.appendChild(this.div);
            break;
        case 4:
            at.getPanes().markerMouseTarget.appendChild(this.div);
            break;
        case 5:
            at.getPanes().floatPane.appendChild(this.div);
            break;
        default:
            at.getPanes().mapPane.appendChild(this.div);
            break
        }
        return this.div
    };
    ab.prototype.draw = function() {
        var at = this.map.pointToOverlayPixel(this.point);
        this.div.style.left = at.x + "px";
        this.div.style.top = at.y + "px"
    };
    ab.prototype.hide = function() {
        this.div.style.display = "none"
    };
    ab.prototype.show = function() {
        this.div.style.display = "block"
    };
    ab.prototype.updateContent = function(at) {
        this.content = at;
        this.div.innerHTML = at
    };
    ab.prototype.updatePosition = function(at) {
        this.point = at;
        this.draw()
    };
    var h = null;
    var C = K.getPanes().labelPane;
    function a() {
        h = document.createElement("div");
        h.className = "webmap_vehicle_hover_info_baidu";
        C.appendChild(h)
    }
    function o(at) {
        var av = SEGUtil.getVehicleMarkerHoverInfo(at.opts,function(html){
        	h.innerHTML = html;
        });
        h.innerHTML = av;
        var au = K.pointToOverlayPixel(at.point);
        h.style.left = (au.x + 5) + "px";
        h.style.top = (au.y - 13) + "px";
        h.style.display = "block";
        h.style.cursor = "pointer";
        h.onmouseover = function() {
        	 h.style.display = "block";
         	 h.style.position="absolute";
         	 h.style.zIndex="2";
         	
         	
        };
        h.onmouseleave = function() {
        	aj();
        }
    }
    function aj() {
        h.style.display = "none";
    }
    var w = "white";
    function am(aw) {
        var av = this;
        this.opts = {};
        for (var au in aw) {
            this.opts[au] = aw[au]
        }
        this.div = new SEGUtil.Div(0, 0, null, null).get();
        this.div.style.zIndex = "1";
        var at = 25;
        this.iconDiv = new SEGUtil.Div( - 13, -13, 25, at).get();
        this.numberPlateDiv = new SEGUtil.Div(13, -13, null, at).get();
        this.div.appendChild(this.iconDiv);
        this.div.appendChild(this.numberPlateDiv);
        this.iconDiv.style.cursor = "pointer";
        this.iconDiv.onmouseover = function(ax) {
            o(av)
        };
        this.iconDiv.onmouseout = function() {
            aj()
        };
        this.updateIcon();
        this.numberPlateDiv.style.padding = "0 5px";
        this.numberPlateDiv.style.whiteSpace = "nowrap";
        this.numberPlateDiv.style.lineHeight = at + "px";
        this.numberPlateDiv.style.border = "1px solid gray";
        this.numberPlateDiv.style.borderRadius = "2px";
        this.numberPlateDiv.style.background = w;
        if (isIE) {
            this.numberPlateDiv.filter = "alpha(opacity=70)"
        } else {
            this.numberPlateDiv.style.opacity = "0.7"
        }
        this.updateNumberPlate()
    }
    am.prototype = new BMap.Overlay();
    am.prototype.initialize = function(at) {
        this.map = at;
        at.getPanes().markerPane.appendChild(this.div);
        return this.div
    };
    am.prototype.draw = function() {
        var ay = this.opts.lon;
        var aw = this.opts.lat;
        var az = new Converter();
        var ax = az.getEncryPoint(parseFloat(ay), parseFloat(aw));
        var av = BaiduConverter.encrypt(ax.x, ax.y);
        if(av.x<0 || av.y<0){
            var c=new Convertor();
            var r1=c.WGS2BD09({lng:ay,lat:aw});
            av.x = r1.lng;
            av.y = r1.lat;
        }
        var au = new BMap.Point(av.x, av.y);
        this.point = au;
        var at = this.map.pointToOverlayPixel(au);
        this.div.style.left = at.x + "px";
        this.div.style.top = at.y + "px"
    };
    am.prototype.updateNumberPlate = function() {
        this.numberPlateDiv.innerHTML = SEGUtil.parseNull(this.opts.label)
    },
    am.prototype.updateIcon = function() {
        var au = this.opts;
        var at = SEGUtil.getVehicleBackground(au.course, au.speed, au.gpsTime, au.isAlarm, au.status, au.isHistory);
        this.iconDiv.style.background = at
    },
    am.prototype.updatePosition = function() {
        this.draw()
    },
    am.prototype.update = function(au) {
        for (var at in au) {
            this.opts[at] = au[at]
        }
        this.updateNumberPlate();
        this.updateIcon();
        this.updatePosition()
    };
    am.prototype.flicker = function(aw, az) {
        var au = aw || 3000;
        var ax = az || "#FF0000";
        var av = w;
        var ay = this.numberPlateDiv;
        var at = setInterval(function() {
            SEGUtil.flickerDiv(ay, av, ax)
        },
        200);
        setTimeout(function() {
            clearInterval(at);
            ay.style.backgroundColor = av
        },
        au)
    };
    function f(ax, aB, aC, aQ) {
        var aK = 0;
        var aI = 0;
        if (typeof(ax) == "object") {
            var aP = ax;
            var aO = new Converter();
            var au = aO.getEncryPoint(parseFloat(aP.lon), parseFloat(aP.lat));
            var at = BaiduConverter.encrypt(au.x, au.y);
            var aJ = new BMap.Point(at.x, at.y);
            this.point = aJ;
            var av = 0;
            var aE = 0;
            var ay = 0;
            var aw = 0;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.div.style.zIndex = 0;
            if (aP.icon) {
                var aA = aP.icon.url;
                av = aP.icon.width;
                aE = aP.icon.height;
                var az = aP.icon.left || 0;
                var aH = aP.icon.top || 0;
                ay = (typeof(aP.icon.anchorx) == "undefined") ? -(av / 2) : (aP.icon.anchorx);
                aw = (typeof(aP.icon.anchory) == "undefined") ? -aE: (aP.icon.anchory);
                aK = (typeof(aP.icon.winx) == "undefined") ? 0 : (aP.icon.winx);
                aI = (typeof(aP.icon.winy) == "undefined") ? -aE + 1 : (aP.icon.winy);
                this.iconDiv = new SEGUtil.Div(ay, aw, av, aE).get();
                this.iconDiv.style.background = "url(" + aA + ") " + az + "px " + aH + "px no-repeat";
                this.iconDiv.style.cursor = "pointer";
                if (aP.title) {
                    this.iconDiv.title = aP.title
                }
                this.div.appendChild(this.iconDiv)
            }
            if (aP.label) {
                var aF = (typeof(aP.label.anchorx) == "undefined") ? av / 2 : aP.label.anchorx;
                var aD = (typeof(aP.label.anchory) == "undefined") ? -aE: aP.label.anchory;
                var aG = aP.label.text;
                this.labelDiv = new SEGUtil.Div(aF, aD, null, null).get();
                this.labelDiv.innerHTML = aG;
                var aN = {
                    whiteSpace: "nowrap",
                    fontSize: "12px"
                };
                var aM = aP.label.style;
                if (aM) {
                    for (var aL in aM) {
                        aN[aL] = aM[aL]
                    }
                }
                for (var aL in aN) {
                    this.labelDiv.style[aL] = aN[aL]
                }
                if (aP.title) {
                    this.labelDiv.title = aP.title
                }
                this.div.appendChild(this.labelDiv)
            }
        } else {
            var aO = new Converter();
            var au = aO.getEncryPoint(parseFloat(ax), parseFloat(aB));
            var at = BaiduConverter.encrypt(au.x, au.y);
            var aJ = new BMap.Point(at.x, at.y);
            this.point = aJ;
            this.label = aC;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.iconDiv = new SEGUtil.Div( - 6, -20, 12, 20).get();
            this.labelDiv = new SEGUtil.Div(8, -20, null, null).get();
            this.labelDiv.style.border = "1px solid red";
            this.labelDiv.innerHTML = aC;
            this.labelDiv.style.fontSize = "12px";
            this.labelDiv.style.padding = "1px";
            this.labelDiv.style.backgroundColor = "white";
            this.labelDiv.style.whiteSpace = "nowrap";
            if (isIE) {
                this.labelDiv.style.filter = "alpha(opacity=65)"
            } else {
                this.labelDiv.style.opacity = "0.65"
            }
            this.iconDiv.style.background = "url(" + SEGUtil.imageRootDir + "/marker.png) no-repeat";
            this.iconDiv.style.cursor = "pointer";
            if (aQ) {
                this.iconDiv.title = aQ
            }
            this.div.appendChild(this.iconDiv);
            this.div.appendChild(this.labelDiv)
        }
        this.winx = aK;
        this.winy = aI
    }
    f.prototype = new BMap.Overlay();
    f.prototype.initialize = function(at) {
        this.map = at;
        at.getPanes().markerPane.appendChild(this.div);
        return this.div
    };
    f.prototype.draw = function() {
        var at = this.map.pointToOverlayPixel(this.point);
        this.div.style.left = at.x + "px";
        this.div.style.top = at.y + "px"
    };
    f.prototype.getZIndex = function() {
        return this.div.style.zIndex
    };
    f.prototype.setZIndex = function(at) {
        this.div.style.zIndex = at
    };
    function aa(au, at) {
        if (au.lng == at.lng && au.lat == at.lat) {
            return 0
        }
        return K.getDistance(au, at)
    }
    function ah(au) {
        var ax = au.getPath();
        var aw = 0;
        for (var av = 0; av < ax.length - 1; av++) {
            var az = ax[av];
            var at = ax[av + 1];
            var ay = aa(az, at);
            aw += ay
        }
        return SEGUtil.getDistanceDesc(aw)
    }
    function Y() {
        this.div = document.createElement("div")
    }
    Y.prototype = new BMap.Overlay();
    Y.prototype.initialize = function(av) {
        var au = this;
        var at = K.getSize();
        this.div.style.cssText = "position:absolute;background:url(about:blank);width:" + at.width + "px;height:" + at.height + "px";
        K.addEventListener("resize",
        function(aw) {
            au.adjustSize(aw.size)
        });
        this.initEvents();
        K.getPanes().floatPane.appendChild(this.div);
        return this.div
    };
    Y.prototype.clearEvents = function() {
        if (document.addEventListener) {
            document.removeEventListener("mousemove", u, false);
            document.removeEventListener("mouseup", J, false)
        } else {
            if (document.attachEvent) {
                document.detachEvent("onmousemove", u);
                document.detachEvent("onmouseup", J)
            }
        }
    };
    Y.prototype.draw = function() {
        point = K.pixelToPoint(new BMap.Pixel(0, 0)),
        pixel = K.pointToOverlayPixel(point);
        this.div.style.left = pixel.x + "px";
        this.div.style.top = pixel.y + "px"
    };
    Y.prototype.adjustSize = function(at) {
        this.div.style.width = at.width + "px";
        this.div.style.height = at.height + "px"
    };
    Y.prototype.getEventPoint = function(av) {
        var ay = window.event || av;
        var ax = ay.target || ay.srcElement;
        var au = ay.offsetX || ay.layerX || 0;
        var az = ay.offsetY || ay.layerY || 0;
        if (ax.nodeType != 1) {
            ax = ax.parentNode
        }
        while (ax && ax != K.getContainer()) {
            if (! (ax.clientWidth == 0 && ax.clientHeight == 0 && ax.offsetParent && ax.offsetParent.nodeName == "TD")) {
                au += ax.offsetLeft || 0;
                az += ax.offsetTop || 0
            }
            ax = ax.offsetParent
        }
        var aw = new BMap.Pixel(au, az);
        var at = K.pixelToPoint(aw);
        return at
    };
    function d(ax, au, aw, at) {
        var av = SEGUtil.sortPoint(ax, au, aw, at);
        var aB = new BMap.Point(av[0], av[3]);
        var aA = new BMap.Point(av[0], av[1]);
        var az = new BMap.Point(av[2], av[1]);
        var ay = new BMap.Point(av[2], av[3]);
        return [aB, aA, az, ay]
    }
    var u = null;
    var J = null;
    Y.prototype.initEvents = function() {
        var au = {
            whiteSpace: "nowrap",
            width: "140px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        if (isIE) {
            au.filter = "alpha(opacity=70)"
        } else {
            au.opacity = "0.7"
        }
        var at = this;
        u = function(ax) {
            var aw = at.getEventPoint(ax);
            switch (X) {
            case "circle":
                if (P == null) {
                    P = new ab(2, aw, $mdtscc, null, au);
                    K.addOverlay(P)
                } else {
                    P.updatePosition(aw)
                }
                if (B != null) {
                    P.updateContent($ctc);
                    var av = aa(I, aw);
                    B.setRadius(av)
                }
                break;
            case "rect":
                if (an == null) {
                    an = new ab(2, aw, $mdtsrs, null, au);
                    K.addOverlay(an)
                } else {
                    an.updatePosition(aw)
                }
                if (Q != null) {
                    an.updateContent($ctc);
                    var ay = d(al.lng, al.lat, aw.lng, aw.lat);
                    Q.setPath(ay)
                }
                break;
            default:
                break
            }
        };
        J = function(aO) {
            var aI = at.getEventPoint(aO);
            H();
            switch (X) {
            case "circle":
                O("pan");
                K.removeOverlay(P);
                if (W && B) {
                    var ax = I.lng;
                    var aC = I.lat;
                    var az = aa(I, aI);
                    var aw = BaiduConverter.decrypt(ax, aC);
                    var av = Deconverter.decode(aw.x, aw.y);
                    var aD = Math.round(av.x * 1000000) / 1000000;
                    var aF = Math.round(av.y * 1000000) / 1000000;
                    var aK = new SEGCircle();
                    aK.target = B;
                    aK.lon = aD;
                    aK.lat = aF;
                    aK.radius = az;
                    aK.id = null;
                    aK.strokeColor = "red";
                    aK.strokeWeight = 2;
                    aK.strokeOpacity = 1;
                    aK.fillColor = "white";
                    aK.fillOpacity = 0.65;
                    aq.push(aK);
                    W(aD, aF, az, aK)
                }
                ae();
                break;
            case "rect":
                O("pan");
                K.removeOverlay(an);
                if (W && Q) {
                    var aM = SEGUtil.sortPoint(al.lng, al.lat, aI.lng, aI.lat);
                    var aH = BaiduConverter.decrypt(aM[0], aM[1]);
                    var aJ = Deconverter.decode(aH.x, aH.y);
                    var aB = Math.round(aJ.x * 1000000) / 1000000;
                    var aG = Math.round(aJ.y * 1000000) / 1000000;
                    var aA = BaiduConverter.decrypt(aM[2], aM[3]);
                    var aE = Deconverter.decode(aA.x, aA.y);
                    var aN = Math.round(aE.x * 1000000) / 1000000;
                    var ay = Math.round(aE.y * 1000000) / 1000000;
                    var aL = new SEGRectangle();
                    aL.target = Q;
                    aL.lon1 = aB;
                    aL.lat1 = aG;
                    aL.lon2 = aN;
                    aL.lat2 = ay;
                    aL.id = null;
                    aL.strokeColor = "red";
                    aL.strokeWeight = 2;
                    aL.strokeOpacity = 1;
                    aL.fillColor = "white";
                    aL.fillOpacity = 0.65;
                    aq.push(aL);
                    W(aB, aG, aN, ay, aL)
                }
                A();
                break;
            default:
                break
            }
        };
        this.div.onmousedown = function(aw) {
            var av = at.getEventPoint(aw);
            switch (X) {
            case "circle":
                if (I == null) {
                    I = av;
                    B = new BMap.Circle(I, 0, {
                        enableClicking: false,
                        enableEditing: false,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 1,
                        fillColor: "white",
                        fillOpacity: 0.65
                    });
                    K.addOverlay(B);
                    if (P != null) {
                        P.updateContent($mtcs)
                    }
                }
                break;
            case "rect":
                if (al == null) {
                    al = av;
                    var ax = d(av.lng, av.lat, av.lng, av.lat);
                    Q = new BMap.Polygon(ax, {
                        enableEditing: false,
                        clickable: false,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 1,
                        fillColor: "white",
                        fillOpacity: 0.65
                    });
                    K.addOverlay(Q);
                    if (an != null) {
                        an.updateContent($mtcs)
                    }
                }
                break;
            default:
                break
            }
        };
        if (document.addEventListener) {
            document.addEventListener("mousemove", u, false);
            document.addEventListener("mouseup", J, false)
        } else {
            if (document.attachEvent) {
                document.attachEvent("onmousemove", u);
                document.attachEvent("onmouseup", J)
            }
        }
    };
    var l = null;
    var i = null;
    var ag = -1;
    var r = null;
    var ao = [];
    var t = null;
    this.setHistoryData = function(au, at) {
        l = au;
        i = at
    };
    this.resetHistory = function() {
        V();
        i = null;
        l = null;
        ag = -1
    };
    function V() {
        if (r != null) {
            K.removeOverlay(r);
            r = null
        }
        if (ao.length > 0) {
            for (var at = 0; at < ao.length; at++) {
                K.removeOverlay(ao[at])
            }
            ao.splice(0, ao.length)
        }
        if (t != null) {
            K.removeOverlay(t);
            t = null
        }
    }
    var T = {
        fontSize: "12px",
        textAlign: "center",
        cursor: "pointer",
        width: "18px",
        height: "18px",
        margin: "-9px 0 0 -9px",
        background: "url(" + SEGUtil.imageRootDir + "/circle_18.png) no-repeat"
    };
    this.playHistoryTo = function(av) {
    	var orderPointFlag  = $("#orderPoints").val();
        if (r == null) {
            var aB = [];
            for (var au = 0; au < i.length; au++) {
                var az = i[au];
                var aw = new Converter();
                var aA = aw.getEncryPoint(parseFloat(az.lon), parseFloat(az.lat));
                var ay = BaiduConverter.encrypt(aA.x, aA.y);
                if(ay.x<0 || ay.y<0){
                    var c=new Convertor();
                    var r1=c.WGS2BD09({lng:az.lon,lat:az.lat});
                    ay.x = r1.lng;
                    ay.y = r1.lat;
                }
                var ax = new BMap.Point(ay.x, ay.y);
                aB.push(ax)
                if(orderPointFlag=="true"){
	                var opts = {
					  position : ax,    // 指定文本标注所在的地理位置
					  offset   : new BMap.Size(0, 0)    //设置文本偏移量
					}
	                var label = new BMap.Label(au+1, opts);  // 创建文本标注对象
	        		label.setStyle({
	        			 color : "red",
	        			 fontWeight :"bold",
	        	         backgroundColor:"1",
	        	         border :"0",
	        	         fontSize : "14px",
	        			 fontFamily:"微软雅黑"
	        		 });
	        		K.addOverlay(label);  
        		} 
            }
            r = new BMap.Polyline(aB, {
                enableEditing: false,
                enableClicking: false,
                strokeColor: "blue",
                strokeWeight: 2,
                strokeOpacity: 1
            });
            K.addOverlay(r)
        }
        if (av == -1) {
            if (t != null) {
                K.removeOverlay(t);
                t = null
            }
            ag = -1;
            return
        }
        if (av < 0 || av >= i.length) {
            return
        }
        var at = i[av];
        if (t == null) {
            for (var au in l) {
                at[au] = l[au]
            }
            t = new am(at);
            K.addOverlay(t)
        } else {
            t.update(at)
        }
        var ax = t.point;
        if (!K.getBounds().containsPoint(ax)) {
            K.setCenter(ax)
        }
    };
    this.getLocation = function(ay, aw, aA) {
        var az = new Converter();
        var ax = az.getEncryPoint(parseFloat(ay), parseFloat(aw));
        var av = BaiduConverter.encrypt(ax.x, ax.y);
        if(av.x<0 || av.y<0){
            var c=new Convertor();
            var r1=c.WGS2BD09({lng:ay,lat:aw});
            av.x = r1.lng;
            av.y = r1.lat;
        }
        
        var at = new BMap.Point(av.x, av.y);
        var au = new BMap.Geocoder();
        au.getLocation(at,
        function(aB) {
            aA(aB.address)
        })
    }
};
/**
 * 历史回放回调函数
 * gpsInfo: 当前播放的gps信息
 *
 * numberPlate
 * callLetter
 * isLoc
 * referencePosition
 * oil
 * lon
 * lat
 * speed
 * course
 * gpsTime
 * stamp
 * isAlarm
 * status
 */
var history_console_auto_scroll = true;
var history_console_scroll_div = null;
var history_console_records = 0;
var history_console_max_records = 1000;
function initHistoryConsole(){
	history_console_scroll_div = document.getElementById("history_table_scroll");
	$("#history_table_scroll").on("scroll", function(){
		var clientHeight = history_console_scroll_div.clientHeight;
		var scrollTop = history_console_scroll_div.scrollTop;
		var scrollHeight = history_console_scroll_div.scrollHeight;
		if((clientHeight + scrollTop) != scrollHeight){
			history_console_auto_scroll = false;
		}else{
			history_console_auto_scroll = true;
		}
	});
}

function playingHistoryCallback(pindex, phead, gpsInfo) {
	convertGpsInfoToGridData1(gpsInfo);
	//if (CamionView) {
		//CamionView.gpsInfoGrid.addRow(convertGpsInfoToGridData1(gpsInfo),CamionView.gpsInfoGrid.rows[0],true);
	//}
}
//*************************************************************
//先拿到经纬度获取到位置，再注入表格中	
function convertGpsInfoToGridData1(gpsInfo){
	var address ;
	var lon = gpsInfo.lon;
	var lat = gpsInfo.lat;
	//通过百度api获取参考位置
	var ggPoint = new BMap.Point(lon,lat);
    var pointArr = [];
    pointArr.push(ggPoint);
    new BMap.Convertor().translate(pointArr, 1, 5, function(data){
    	//if(data.status == 0){
    		new BMap.Geocoder().getLocation(new BMap.Point(data.points[0].lng,data.points[0].lat), function(rs){
    			var lonlat=data.points[0].lat+','+data.points[0].lng;
 	      		var lonlaturl="http://api.map.baidu.com/geocoder/v2/?ak=oRTEhIarHzXK52qPGuFKQGAPqKIrnmdn&callback=renderReverse&location="+lonlat+"&output=json&pois=0";
 	 	    	$.ajax({                                             
 	 				type:'get',
 	 				url:lonlaturl,
 	 				success:function(data){
 	 					address = data.result.formatted_address+','+data.result.sematic_description;
		  	   		    if(address == ''){
		  	   			   address = '参考位置为空';
		  	   		    }
		  	   		   if (CamionView) {
		  	   		       CamionView.gpsInfoGrid.addRow(convertGpsInfoToGridData(gpsInfo,address),CamionView.gpsInfoGrid.rows[0],true);
		  	   		   }
 	 				},
 	 				error:function(error){
 	 					console.log('请求参考位置出错!');
 	 				},
 	 				dataType:'jsonp'
 	 			});
          	});
    });
}

function convertGpsInfoToGridData(gpsInfo,address){
	var row={};
	row.numberPlate = gpsInfo.numberPlate;
	row.callLetter = gpsInfo.callLetter;
	if(gpsInfo.flag==1){
		row.isLoc = "卫星定位";
	}else if(gpsInfo.flag==2){
		row.isLoc = "基站定位";
	}else{
		row.isLoc = "正在定位";
	}
	row.status = "";
	//状态特殊处理，只显示点火、熄火状态
//	console.log("gpsInfo.status:"+gpsInfo.status);
	var statusArr = new Array();
	for ( var status in gpsInfo.status) {
		if(gpsInfo.status[status] ==23){
			statusArr.push(23);
			break;
		}else if(gpsInfo.status[status] ==33){
			statusArr.push(33);
			break;
		}
	}
	row.id = 'GPS-info:'+gpsInfo.callLetter +','+gpsInfo.lat+','+gpsInfo.lon;
	row.status = SEGUtil.parseVehicleStatus(statusArr);
	for(var status in gpsInfo.status){
		if(gpsInfo.status[status] ==3){
			row.status += "、欠压";
			break;
		}else if(gpsInfo.status[status] ==4){
			row.status += "、主电被切断";
			break;
		}else if(gpsInfo.status[status] ==11){
			row.status += "、拆机";
			break;
		}
	};   
	//row.status = SEGUtil.parseVehicleStatus(gpsInfo.status);
	row.referencePosition = address;
//	console.log(row.referencePosition);
	row.lon = gpsInfo.lon;
	row.lat = gpsInfo.lat;
	row.speed = gpsInfo.speed;
	row.gpsTime = gpsInfo.gpsTime;
	row.course = SEGUtil.getCourseDesc(gpsInfo.course);
	row.accTimeLen = gpsInfo.accTimeLen;
	row.electricity = gpsInfo.electricity;
	row.status1 = gpsInfo.status;
	row.totalDistance = gpsInfo.totalDistance;
	row.oil = gpsInfo.oil;
	return row;
}

/**
 * 播放新的历史回放回调函数
 */
function newHistoryCallback(){
	$("#history_table tbody tr").remove();
	history_console_records = 0;
	$("#consolediv_tabs li:last").css("display", "block");
}

/**
 * 关闭历史回放回调函数
 */
function closeHistoryCallback(){
	$("#consolediv_tabs ul li:last").css("display", "none");
	$("#history_table tbody tr").remove();
	history_console_records = 0;
//	clearAllMarker();
}
function baiduMapSearchNearby(a, f, d, b, i) {
    var l = {
        pageCapacity: 99,
        onSearchComplete: function(x) {
            if (!i) {
                return
            }
            var c = x.getCurrentNumPois();
            var p = [];
            for (var o = 0; o < c; o++) {
                var m = x.getPoi(o);
                var r = m.title;
                var s = m.address;
                var t = m.point;
                var q = t.lng;
                var n = t.lat;
                var w = BaiduConverter.decrypt(q, n);
                var u = Deconverter.decode(w.x, w.y);
                var v = {
                    decodeLng: u.x,
                    decodeLat: u.y,
                    name: r,
                    address: s
                };
                p.push(v)
            }
            i(p)
        }
    };
    var e = new Converter();
    var j = e.getEncryPoint(parseFloat(a), parseFloat(f));
    var h = BaiduConverter.encrypt(j.x, j.y);
    var g = new BMap.Point(h.x, h.y);
    var k = new BMap.LocalSearch(g, l);
    k.searchNearby(b, g, d)
}
var GoogleMapProxy = function(k, n) {
    var ad = this;
    var D = n;
    var aj = [];
    var E = [];
    var x = [];
    var C = [aj, E];
    var g = {
        mapTypeControl: true,
        overviewMapControl: true,
        scaleControl: true,
        scaleControlOptions: {
            position: google.maps.ControlPosition.BOTTOM_LEFT
        },
        center: new google.maps.LatLng(22.553102, 114.110901),
        zoom: 14,
        streetViewControl: true,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var G = new google.maps.Map(document.getElementById(k), g);
    var z = new c();
    G.controls[google.maps.ControlPosition.TOP_RIGHT].push(z.getDiv());
    this.init = function() {
        a();
        N()
    };
    var J = null;
    function d(al) {
        var am = {
            zIndex: "5",
            padding: "5px 2px 2px 5px",
            margin: "-20px 0 0 -6px",
            cursor: "pointer",
            border: "1px solid #6482B9"
        };
        if (isIE) {
            am.filter = "alpha(opacity=80)"
        } else {
            am.opacity = "0.8"
        }
        J = new V(al, "删除标注", null, am);
        J.setMap(G);
        J.triggerMarker = null;
        Z();
        google.maps.event.addDomListener(J.div, "click",
        function() {
            D.deleteStaticMarker(J.triggerMarker);
            J.hide()
        })
    }
    var b = function() {
        if (J != null) {
            J.hide()
        }
    };
    function Z() {
        if (document.addEventListener) {
            document.addEventListener("click", b, false)
        } else {
            if (document.attachEvent) {
                document.attachEvent("onclick", b)
            }
        }
    }
    function W() {
        if (document.addEventListener) {
            document.removeEventListener("click", b, false)
        } else {
            if (document.attachEvent) {
                document.detachEvent("onclick", b)
            }
        }
    }
    this.destroyMap = function() {
        W()
    };
    this.getCenter = function() {
        var al = G.getCenter();
        var ao = al.lng();
        var an = al.lat();
        var am = Deconverter.decode(ao, an);
        return {
            lon: am.x,
            lat: am.y
        }
    };
    this.isPointInView = function(ao, an) {
        var ap = new Converter();
        var am = ap.getEncryPoint(parseFloat(ao), parseFloat(an));
        var al = new google.maps.LatLng(am.y, am.x);
        return G.getBounds().contains(al)
    };
    this.getZoom = function() {
        return G.getZoom()
    };
    this.setCenter = function(ao, an) {
        var ap = new Converter();
        var am = ap.getEncryPoint(parseFloat(ao), parseFloat(an));
        var al = new google.maps.LatLng(am.y, am.x);
        G.setCenter(al)
    };
    this.centerAndZoom = function(ao, an, aq) {
        var ap = new Converter();
        var am = ap.getEncryPoint(parseFloat(ao), parseFloat(an));
        var al = new google.maps.LatLng(am.y, am.x);
        G.setCenter(al);
        G.setZoom(aq)
    };
    this.fitBounds = function(at, ap, ar, am) {
        var aq = new Converter();
        var ax = aq.getEncryPoint(parseFloat(at), parseFloat(ap));
        var an = new Converter();
        var aw = an.getEncryPoint(parseFloat(ar), parseFloat(am));
        var ao = SEGUtil.sortPoint(ax.x, ax.y, aw.x, aw.y);
        var av = new google.maps.LatLng(ao[1], ao[0]);
        var au = new google.maps.LatLng(ao[3], ao[2]);
        var al = new google.maps.LatLngBounds(av, au);
        G.fitBounds(al)
    };
    this.openDistanceTool = function() {
        K("rule")
    };
    this.openCoordTool = function() {
        K("coord")
    };
    this.drawPoint = function(al) {
        R = al;
        K("point")
    };
    this.drawCircle = function(al) {
        R = al;
        K("circle")
    };
    this.drawRectangle = function(al) {
        R = al;
        K("rect")
    };
    this.drawPolygon = function(al) {
        R = al;
        K("polygon")
    };
    this.drawPolyline = function(al) {
        R = al;
        K("polyline")
    };
    this.resize = function() {
        google.maps.event.trigger(G, "resize")
    };
    this.newSimpleMarker = function(ar, ap, ao, aq, at) {
        var am = new SEGSimpleMarker();
        var al = new e(ar, ap, ao, aq);
        am.target = al;
        if (typeof(ar) == "object") {
            var an = ar;
            am.config = an;
            am.lon = an.lon;
            am.lat = an.lat;
            am.label = an.label;
            am.title = an.title;
            am.id = an.id
        } else {
            am.lon = ar;
            am.lat = ap;
            am.label = ao;
            am.title = aq;
            am.id = at
        }
        return am
    };
    this.setSimpleMarkerIcon = function(ax, am) {
        if (!this.isMarkerOnMap(ax)) {
            return
        }
        var an = am.url;
        var ao = am.width;
        var aw = am.height;
        var aq = am.left || 0;
        var av = am.top || 0;
        var au = (typeof(am.anchorx) == "undefined") ? -(ao / 2) : (am.anchorx);
        var at = (typeof(am.anchory) == "undefined") ? -aw: (am.anchory);
        var ar = (typeof(am.winx) == "undefined") ? 0 : (am.winx);
        var ap = (typeof(am.winy) == "undefined") ? -aw + 1 : (am.winy);
        var al = ax.target.iconDiv;
        al.style.width = ao + "px";
        al.style.height = aw + "px";
        al.style.background = "url(" + an + ") " + aq + "px " + av + "px no-repeat";
        al.style.left = au + "px";
        al.style.top = at + "px";
        ax.target.winx = ar;
        ax.target.winy = ap
    };
    this.toTop = function(al, am) {
        al.target.setZIndex(am ? 1 : 0)
    };
    var O = [];
    this.newInfoWindow = function(av, au, an, ax, am) {
        an -= 35;
        ax -= 18;
        var ao = null;
        var ap = null;
        var aw = 8;
        var ar = 35;
        if (typeof(av) == "object") {
            ao = av
        } else {
            ao = new SEGUtil.Div(0, aw, null, ar).get();
            ao.style.width = "100%";
            ao.style.padding = "0 5px 0 5px";
            ao.style.borderBottom = "1px solid #ccc";
            ao.style.fontWeight = "bolder";
            ao.innerHTML = av
        }
        var al = new SEGUtil.Div(0, 0, an, ax).get();
        al.appendChild(ao);
        if (typeof(au) == "object") {
            ap = au
        } else {
            ap = new SEGUtil.Div(0, 0, an, ax - ar - aw).get()
        }
        ap.style.top = (ar + aw) + "px";
        al.appendChild(ap);
        var at = new google.maps.InfoWindow({
            content: al
        });
        var aq = new SEGInfoWindow();
        aq.target = at;
        aq.titleDiv = ao;
        O.push(aq);
        return aq
    };
    this.showInfoWindow = function(am, al) {
        al.target.setOptions({
            position: am.target.point,
            pixelOffset: new google.maps.Size(am.target.winx, am.target.winy)
        });
        al.target.open(G)
    };
    this.setInfoWindowTitle = function(al, am) {
        al.titleDiv.innerHTML = "<b>" + am + "</b>"
    };
    this.isInfoWindowExist = function(am) {
        var al = SEGUtil.indexOfArray(O, am);
        return al != -1
    };
    this.closeInfoWindow = function(al) {
        if (!this.isInfoWindowExist(al)) {
            return
        }
        al.target.close()
    };
    this.closeAllInfoWindow = function() {
        for (var al = 0; al < O.length; al++) {
            O[al].target.close()
        }
    };
    this.addEventListener = function(an, al, am) {
        if (SEGSimpleMarker.prototype.isPrototypeOf(an)) {
            google.maps.event.addDomListener(an.target.div, al, am)
        }
    };
    this.newVehicleMarker = function(an) {
        var al = new af(an);
        var am = new SEGVehicleMarker();
        am.target = al;
        am.opts = an;
        return am
    };
    this.newCircle = function(am, aq, an, av, ax, aC, al, ao, az) {
        var aD = new Converter();
        var aw = aD.getEncryPoint(parseFloat(am), parseFloat(aq));
        var ay = new google.maps.LatLng(aw.y, aw.x);
        var au = typeof(aC) == "undefined" ? 2 : aC;
        var aB = typeof(al) == "undefined" ? 1 : al;
        var ar = typeof(az) == "undefined" ? 0.65 : az;
        var at = {
            center: ay,
            radius: an,
            clickable: false,
            draggable: false,
            editable: false,
            visible: true,
            fillColor: ao || "white",
            fillOpacity: ar,
            strokeColor: ax || "blue",
            strokeOpacity: aB,
            strokeWeight: au
        };
        var ap = new google.maps.Circle(at);
        var aA = new SEGCircle();
        aA.target = ap;
        aA.lon = am;
        aA.lat = aq;
        aA.radius = an;
        aA.id = av;
        aA.strokeColor = ax;
        aA.strokeWeight = aC;
        aA.strokeOpacity = al;
        aA.fillColor = ao;
        aA.fillOpacity = az;
        return aA
    };
    this.newRectangle = function(aG, ao, aE, am, aw, ax, aB, an, ap, az) {
        var aF = SEGUtil.sortPoint(parseFloat(aG), parseFloat(ao), parseFloat(aE), parseFloat(am));
        var aH = new Converter();
        var aC = aH.getEncryPoint(aF[0], aF[1]);
        var aI = new google.maps.LatLng(aC.y, aC.x);
        var au = new Converter();
        var at = au.getEncryPoint(aF[2], aF[3]);
        var ay = new google.maps.LatLng(at.y, at.x);
        var av = typeof(aB) == "undefined" ? 2 : aB;
        var aA = typeof(an) == "undefined" ? 1 : an;
        var aq = typeof(az) == "undefined" ? 0.65 : az;
        var ar = {
            bounds: new google.maps.LatLngBounds(aI, ay),
            clickable: false,
            draggable: false,
            editable: false,
            visible: true,
            fillColor: ap || "white",
            fillOpacity: aq,
            strokeColor: ax || "blue",
            strokeOpacity: aA,
            strokeWeight: av
        };
        var al = new google.maps.Rectangle(ar);
        var aD = new SEGRectangle();
        aD.target = al;
        aD.lon1 = aG;
        aD.lat1 = ao;
        aD.lon2 = aE;
        aD.lat2 = am;
        aD.id = aw;
        aD.strokeColor = ax;
        aD.strokeWeight = aB;
        aD.strokeOpacity = an;
        aD.fillColor = ap;
        aD.fillOpacity = az;
        return aD
    };
    this.newPolygon = function(ax, at, av, aC, am, an, az) {
        var aw = [];
        for (var aA = 0; aA < ax.length; aA++) {
            var aD = ax[aA];
            var aE = new Converter();
            var au = aE.getEncryPoint(parseFloat(aD.lon), parseFloat(aD.lat));
            var ay = new google.maps.LatLng(au.y, au.x);
            aw.push(ay)
        }
        var ar = typeof(aC) == "undefined" ? 2 : aC;
        var aB = typeof(am) == "undefined" ? 1 : am;
        var ao = typeof(az) == "undefined" ? 0.65 : az;
        var aq = {
            paths: aw,
            clickable: false,
            draggable: false,
            editable: false,
            visible: true,
            fillColor: an || "white",
            fillOpacity: ao,
            strokeColor: av || "blue",
            strokeOpacity: aB,
            strokeWeight: ar
        };
        var ap = new google.maps.Polygon(aq);
        var al = new SEGPolygon();
        al.target = ap;
        al.ps = ax;
        al.id = at;
        al.strokeColor = av;
        al.strokeWeight = aC;
        al.strokeOpacity = am;
        al.fillColor = an;
        al.fillOpacity = az;
        return al
    };
    this.newPolyline = function(am, ao, aB, ar, au) {
        var aA = [];
        for (var aq = 0; aq < am.length; aq++) {
            var ax = am[aq];
            var av = new Converter();
            var an = av.getEncryPoint(parseFloat(ax.lon), parseFloat(ax.lat));
            var az = new google.maps.LatLng(an.y, an.x);
            aA.push(az)
        }
        var ay = typeof(ar) == "undefined" ? 2 : ar;
        var ap = typeof(au) == "undefined" ? 1 : au;
        var al = {
            path: aA,
            clickable: false,
            draggable: false,
            editable: false,
            visible: true,
            strokeColor: aB || "blue",
            strokeOpacity: ap,
            strokeWeight: ay
        };
        var aw = new google.maps.Polyline(al);
        var at = new SEGPolyline();
        at.target = aw;
        at.ps = am;
        at.id = ao;
        at.strokeColor = aB;
        at.strokeWeight = ar;
        at.strokeOpacity = au;
        return at
    };
    this.addMarker = function(al, am) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(al)) {
            al.target.setMap(G);
            x.push(al);
            return
        }
        var an = null;
        if (typeof(am) == "undefined" || am == 0) {
            an = aj
        } else {
            if (am == 1) {
                an = E
            }
        }
        if (an) {
            al.target.setMap(G);
            an.push(al)
        }
        if (typeof(am) != "undefined" && am == 1 && SEGSimpleMarker.prototype.isPrototypeOf(al)) {
            google.maps.event.addDomListener(al.target.iconDiv, "contextmenu",
            function(ao) {
                if (J == null) {
                    d(al.target.point)
                } else {
                    J.updatePosition(al.target.point);
                    J.show()
                }
                J.triggerMarker = al
            })
        }
    };
    this.copyMarker = function(al) {
        switch (al.markerType) {
        case 1:
            if (al.config) {
                return this.newSimpleMarker(al.config)
            }
            return this.newSimpleMarker(al.lon, al.lat, al.label, al.title, al.id);
        case 2:
            return this.newVehicleMarker(al.opts);
        case 3:
            return this.newCircle(al.lon, al.lat, al.radius, al.id, al.strokeColor, al.strokeWeight, al.strokeOpacity, al.fillColor, al.fillOpacity);
        case 4:
            return this.newRectangle(al.lon1, al.lat1, al.lon2, al.lat2, al.id, al.strokeColor, al.strokeWeight, al.strokeOpacity, al.fillColor, al.fillOpacity);
        case 5:
            return this.newPolygon(al.ps, al.id, al.strokeColor, al.strokeWeight, al.strokeOpacity, al.fillColor, al.fillOpacity);
        case 6:
            return this.newPolyline(al.ps, al.id, al.strokeColor, al.strokeWeight, al.strokeOpacity);
        default:
            return null
        }
    };
    this.isMarkerOnMap = function(al) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(al)) {
            for (var an = 0; an < x.length; an++) {
                if (x[an] == al) {
                    return true
                }
            }
        } else {
            for (var an = 0; an < C.length; an++) {
                var ao = C[an];
                for (var am = 0; am < ao.length; am++) {
                    if (ao[am] == al) {
                        return true
                    }
                }
            }
        }
        return false
    };
    function j(am) {
        for (var al = 0; al < x.length; al++) {
            if (x[al].opts.id == am) {
                return x[al]
            }
        }
        return null
    }
    this.addOrUpdateVehicleMarkerById = function(am) {
        var ao = am.id;
        var al = j(ao);
        if (al != null) {
            al.target.update(am);
            return al
        }
        var an = this.newVehicleMarker(am);
        this.addMarker(an);
        return an
    };
    this.removeMarker = function(al) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(al)) {
            for (var an = 0; an < x.length; an++) {
                if (x[an] == al) {
                    al.target.setMap(null);
                    x.splice(an, 1);
                    return true
                }
            }
        } else {
            for (var an = 0; an < C.length; an++) {
                var ao = C[an];
                for (var am = 0; am < ao.length; am++) {
                    if (ao[am] == al) {
                        al.target.setMap(null);
                        ao.splice(am, 1);
                        return true
                    }
                }
            }
        }
        return false
    };
    this.clearNonStaticMarkers = function() {
        for (var al = 0; al < aj.length; al++) {
            aj[al].target.setMap(null)
        }
        aj.splice(0, aj.length)
    };
    this.clearStaticMarkers = function() {
        for (var al = 0; al < E.length; al++) {
            E[al].target.setMap(null)
        }
        E.splice(0, E.length)
    };
    this.clearVehicleMarkers = function() {
        for (var al = 0; al < x.length; al++) {
            x[al].target.setMap(null)
        }
        x.splice(0, x.length)
    };
    this.getNonStaticMarkers = function() {
        return aj
    };
    this.getStaticMarkers = function() {
        return E
    };
    this.getVehicleMarkers = function() {
        return x
    };
    var S = null;
    var R = null;
    var ai = null;
    var l = null;
    var Q = null;
    var T = null;
    function ah() {
        ai = null;
        l = null;
        Q = null;
        T = null
    }
    function H(al) {
        for (var am = 0; am < al.length; am++) {
            al[am].setMap(null)
        }
    }
    var F = null;
    var L = null;
    var B = null;
    function Y() {
        F = null;
        B = null;
        L = null
    }
    var ae = null;
    var ag = null;
    var M = null;
    function A() {
        ae = null;
        ag = null;
        M = null
    }
    var r = null;
    var ak = null;
    var f = null;
    function X() {
        r = null;
        ak = null;
        f = null
    }
    var y = null;
    var q = null;
    var w = null;
    function t() {
        y = null;
        q = null;
        w = null
    }
    var I = null;
    var o = null;
    function N() {
        var ao = {
            zIndex: "1",
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -6px",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.png) no-repeat -25px -312px"
        };
        var an = {
            zIndex: "1",
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -20px",
            cursor: "pointer",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.gif) no-repeat 0px -14px"
        };
        var ap = {
            zIndex: "1",
            whiteSpace: "nowrap",
            color: "#333333",
            height: "20px",
            border: "1px solid #333333",
            margin: "-6px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var aq = {
            zIndex: "4",
            width: "120px",
            border: "1px solid red",
            margin: "20px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var am = {
            zIndex: "4",
            width: "140px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        var al = {
            zIndex: "4",
            whiteSpace: "nowrap",
            width: "160px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        if (isIE) {
            ap.filter = "alpha(opacity=70)";
            aq.filter = "alpha(opacity=70)"
        } else {
            ap.opacity = "0.7";
            aq.opacity = "0.7"
        }
        google.maps.event.addDomListener(G, "click",
        function(ax) {
            var aA = ax.latLng;
            switch (S) {
            case "point":
                K("pan");
                if (o != null) {
                    o.setMap(null);
                    o = null
                }
                if (R) {
                    var aC = Deconverter.decode(aA.lng(), aA.lat());
                    var at = Math.round(aC.x * 1000000) / 1000000;
                    var az = Math.round(aC.y * 1000000) / 1000000;
                    R(at, az)
                }
                break;
            case "polygon":
                if (ak == null) {
                    var aD = [aA];
                    var ar = {
                        paths: aD,
                        clickable: false,
                        draggable: false,
                        editable: false,
                        visible: true,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 1,
                        fillColor: "white",
                        fillOpacity: 0.65
                    };
                    ak = new google.maps.Polygon(ar);
                    ak.setMap(G)
                } else {
                    var aE = ak.getPath();
                    if (f != null) {
                        aE.pop();
                        f = null
                    }
                    aE.push(aA);
                    ak.setPath(aE)
                }
                break;
            case "polyline":
                if (q == null) {
                    var aE = [aA];
                    var ar = {
                        path: aE,
                        clickable: false,
                        draggable: false,
                        editable: false,
                        visible: true,
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 1
                    };
                    q = new google.maps.Polyline(ar);
                    q.setMap(G)
                } else {
                    var aE = q.getPath();
                    if (w != null) {
                        aE.pop();
                        w = null
                    }
                    aE.push(aA);
                    q.setPath(aE)
                }
                break;
            case "rule":
                if (ai == null) {
                    l = [];
                    var av = {
                        clickable: false,
                        path: [aA],
                        strokeColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 0.6,
                        map: G
                    };
                    ai = new google.maps.Polyline(av);
                    var aB = new V(aA, $sp, null, ap);
                    aB.setMap(G);
                    l.push(ai);
                    l.push(aB)
                } else {
                    var ay = ai.getPath();
                    if (T != null) {
                        ay.pop();
                        T = null
                    }
                    ay.push(aA);
                    ai.setPath(ay);
                    var aw = ab(ai);
                    var aB = new V(aA, aw[0] + aw[1], null, ap);
                    aB.setMap(G);
                    l.push(aB)
                }
                var au = new V(aA, null, null, ao);
                au.setMap(G);
                l.push(au);
                break;
            default:
                break
            }
        });
        google.maps.event.addListener(G, "rightclick",
        function(at) {
            switch (S) {
            case "polygon":
                if (ak != null) {
                    var ar = ak.getPath();
                    if (f != null) {
                        ar.pop();
                        f = null
                    }
                    ar.pop();
                    ak.setPath(ar)
                }
                break;
            case "polyline":
                if (q != null) {
                    var ar = q.getPath();
                    if (w != null) {
                        ar.pop();
                        w = null
                    }
                    ar.pop();
                    q.setPath(ar)
                }
                break;
            default:
                break
            }
        });
        google.maps.event.addListener(G, "mousemove",
        function(az) {
            var aD = az.latLng;
            switch (S) {
            case "point":
                var aE = Deconverter.decode(aD.lng(), aD.lat());
                var au = Math.round(aE.x * 1000000) / 1000000;
                var aB = Math.round(aE.y * 1000000) / 1000000;
                var av = "<span>" + au + ", " + aB + "</span><div style='color:#808080'>" + $ctsp + "</div>";
                if (o == null) {
                    o = new V(aD, av, null, am);
                    o.setMap(G)
                } else {
                    o.updatePosition(aD);
                    o.updateContent(av)
                }
                break;
            case "circle":
                if (L == null) {
                    L = new V(aD, $mdtscc, null, am);
                    L.setMap(G)
                } else {
                    L.updatePosition(aD)
                }
                if (B != null) {
                    L.updateContent($ctc);
                    var aG = U(F, aD);
                    B.setRadius(aG)
                }
                break;
            case "rect":
                if (ag == null) {
                    ag = new V(aD, $mdtsrs, null, am);
                    ag.setMap(G)
                } else {
                    ag.updatePosition(aD)
                }
                if (M != null) {
                    ag.updateContent($ctc);
                    var ax = SEGUtil.sortPoint(ae.lng(), ae.lat(), aD.lng(), aD.lat());
                    var aC = new google.maps.LatLng(ax[1], ax[0]);
                    var aw = new google.maps.LatLng(ax[3], ax[2]);
                    var ar = new google.maps.LatLngBounds(aC, aw);
                    M.setBounds(ar)
                }
                break;
            case "polygon":
                if (r == null) {
                    var av = "<span>" + $ctspgp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    r = new V(aD, av, null, al);
                    r.setMap(G)
                } else {
                    r.updatePosition(aD)
                }
                if (ak != null) {
                    var aF = ak.getPath();
                    if (f != null) {
                        aF.pop();
                        f = null
                    }
                    f = aD;
                    aF.push(f);
                    ak.setPath(aF)
                }
                break;
            case "polyline":
                if (y == null) {
                    var av = "<span>" + $ctsplp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    y = new V(aD, av, null, al);
                    y.setMap(G)
                } else {
                    y.updatePosition(aD)
                }
                if (q != null) {
                    var aF = q.getPath();
                    if (w != null) {
                        aF.pop();
                        w = null
                    }
                    w = aD;
                    aF.push(w);
                    q.setPath(aF)
                }
                break;
            case "rule":
                if (Q == null) {
                    Q = new V(aD, $cts, null, aq);
                    Q.setMap(G)
                } else {
                    Q.updatePosition(aD)
                }
                if (ai != null) {
                    var aF = ai.getPath();
                    if (T != null) {
                        aF.pop();
                        T = null
                    }
                    T = az.latLng;
                    aF.push(T);
                    var ay = ab(ai);
                    var av = $ttd + "：<span style='color:red'>" + ay[0] + "</span>" + ay[1] + "<div style='color:#333333'>" + $ca + "</div>";
                    Q.updateContent(av)
                }
                break;
            case "coord":
                var at = aD.lng();
                var aA = aD.lat();
                var aE = Deconverter.decode(at, aA);
                var au = Math.round(aE.x * 1000000) / 1000000;
                var aB = Math.round(aE.y * 1000000) / 1000000;
                var av = "<span>" + au + ", " + aB + "</span><div style='color:#808080'>" + $dcts + "</div>";
                if (I == null) {
                    I = new V(aD, av, null, am);
                    I.setMap(G)
                } else {
                    I.updatePosition(aD);
                    I.updateContent(av)
                }
                break;
            default:
                break
            }
        });
        google.maps.event.addListener(G, "dblclick",
        function(ax) {
            switch (S) {
            case "rule":
                ax.stop();
                K("pan");
                var az = ax.latLng;
                var aB = new V(az, null, null, an);
                aB.setMap(G);
                l.push(aB);
                var aD = l;
                google.maps.event.addDomListener(aB.div, "click",
                function(aF) {
                    H(aD)
                });
                Q.setMap(null);
                ah();
                break;
            case "polygon":
                K("pan");
                r.setMap(null);
                if (R && ak) {
                    var ar = [];
                    var aE = ak.getPath();
                    for (var av = 0; av < aE.length; av++) {
                        var au = aE.getAt(av);
                        var aA = Deconverter.decode(au.lng(), au.lat());
                        var at = Math.round(aA.x * 1000000) / 1000000;
                        var ay = Math.round(aA.y * 1000000) / 1000000;
                        ar.push({
                            lon: at,
                            lat: ay
                        })
                    }
                    var aC = new SEGPolygon();
                    aC.target = ak;
                    aC.ps = ar;
                    aC.id = null;
                    aC.strokeColor = "red";
                    aC.strokeWeight = 2;
                    aC.strokeOpacity = 1;
                    aC.fillColor = "white";
                    aC.fillOpacity = 0.65;
                    aj.push(aC);
                    R(ar, aC)
                }
                X();
                break;
            case "polyline":
                K("pan");
                y.setMap(null);
                if (R && q) {
                    var ar = [];
                    var aE = q.getPath();
                    for (var av = 0; av < aE.length; av++) {
                        var au = aE.getAt(av);
                        var aA = Deconverter.decode(au.lng(), au.lat());
                        var at = Math.round(aA.x * 1000000) / 1000000;
                        var ay = Math.round(aA.y * 1000000) / 1000000;
                        ar.push({
                            lon: at,
                            lat: ay
                        })
                    }
                    var aw = new SEGPolyline();
                    aw.target = q;
                    aw.ps = ar;
                    aw.id = null;
                    aw.strokeColor = "red";
                    aw.strokeWeight = 2;
                    aw.strokeOpacity = 1;
                    aj.push(aw);
                    R(ar, aw)
                }
                t();
                break;
            case "coord":
                ax.stop();
                K("pan");
                if (I != null) {
                    I.setMap(null);
                    I = null
                }
                break;
            default:
                break
            }
        });
        google.maps.event.addListener(G, "mouseup",
        function(az) {
            var aG = az.latLng;
            switch (S) {
            case "circle":
                az.stop();
                K("pan");
                L.setMap(null);
                if (R && B) {
                    var ar = F.lng();
                    var aD = F.lat();
                    var ay = U(F, aG);
                    var aH = Deconverter.decode(ar, aD);
                    var at = Math.round(aH.x * 1000000) / 1000000;
                    var aE = Math.round(aH.y * 1000000) / 1000000;
                    var aB = new SEGCircle();
                    aB.target = B;
                    aB.lon = at;
                    aB.lat = aE;
                    aB.radius = ay;
                    aB.id = null;
                    aB.strokeColor = "red";
                    aB.strokeWeight = 2;
                    aB.strokeOpacity = 1;
                    aB.fillColor = "white";
                    aB.fillOpacity = 0.65;
                    aj.push(aB);
                    R(at, aE, ay, aB)
                }
                Y();
                break;
            case "rect":
                az.stop();
                K("pan");
                ag.setMap(null);
                if (R && M) {
                    var ax = SEGUtil.sortPoint(ae.lng(), ae.lat(), aG.lng(), aG.lat());
                    var aC = Deconverter.decode(ax[0], ax[1]);
                    var av = Math.round(aC.x * 1000000) / 1000000;
                    var aI = Math.round(aC.y * 1000000) / 1000000;
                    var au = Deconverter.decode(ax[2], ax[3]);
                    var aF = Math.round(au.x * 1000000) / 1000000;
                    var aw = Math.round(au.y * 1000000) / 1000000;
                    var aA = new SEGRectangle();
                    aA.target = M;
                    aA.lon1 = av;
                    aA.lat1 = aI;
                    aA.lon2 = aF;
                    aA.lat2 = aw;
                    aA.id = null;
                    aA.strokeColor = "red";
                    aA.strokeWeight = 2;
                    aA.strokeOpacity = 1;
                    aA.fillColor = "white";
                    aA.fillOpacity = 0.65;
                    aj.push(aA);
                    R(av, aI, aF, aw, aA)
                }
                A();
                break;
            default:
                break
            }
        });
        google.maps.event.addListener(G, "mousedown",
        function(ay) {
            var at = ay.latLng;
            switch (S) {
            case "circle":
                if (F == null) {
                    F = at;
                    var au = {
                        center: at,
                        clickable: false,
                        editable: false,
                        fillColor: "white",
                        fillOpacity: 0.65,
                        radius: 0,
                        strokeColor: "red",
                        strokeOpacity: 1,
                        strokeWeight: 2,
                        visible: true
                    };
                    B = new google.maps.Circle(au);
                    B.setMap(G);
                    if (L != null) {
                        L.updateContent($mtcs)
                    }
                }
                break;
            case "rect":
                if (ae == null) {
                    ae = at;
                    var ar = new google.maps.LatLng(at.lat, at.lng);
                    var ax = new google.maps.LatLng(at.lat, at.lng);
                    var aw = new google.maps.LatLngBounds(ar, ax);
                    var av = {
                        bounds: aw,
                        clickable: false,
                        draggable: false,
                        editable: false,
                        visible: true,
                        fillColor: "white",
                        fillOpacity: 0.65,
                        strokeColor: "red",
                        strokeOpacity: 1,
                        strokeWeight: 2
                    };
                    M = new google.maps.Rectangle(av);
                    M.setMap(G);
                    if (ag != null) {
                        ag.updateContent($mtcs)
                    }
                }
                break;
            default:
                break
            }
        })
    }
    var K = function(am, al) {
        S = am;
        switch (S) {
        case "pan":
            G.setOptions({
                draggable:
                true,
                draggableCursor: null,
                draggingCursor: null
            });
            setTimeout(function() {
                G.setOptions({
                    disableDoubleClickZoom: false
                })
            },
            200);
            break;
        case "point":
            G.setOptions({
                draggableCursor:
                "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "circle":
            G.setOptions({
                draggable:
                false,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "rect":
            G.setOptions({
                draggable:
                false,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "polygon":
            G.setOptions({
                disableDoubleClickZoom:
                true,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "polyline":
            G.setOptions({
                disableDoubleClickZoom:
                true,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "rule":
            G.setOptions({
                draggableCursor:
                "url('" + SEGUtil.imageRootDir + "/ruler.cur'), auto",
                draggingCursor: "url('" + SEGUtil.imageRootDir + "/ruler.cur'), auto",
                disableDoubleClickZoom: true
            });
            break;
        case "coord":
            G.setOptions({
                draggableCursor:
                "crosshair",
                draggingCursor: "crosshair",
                disableDoubleClickZoom: true
            });
            break;
        default:
            G.setOptions({
                draggableCursor:
                null,
                draggingCursor: null
            });
            break
        }
    };
    function V(al, ao, ap, an, am) {
        this.point = al;
        this.content = ao;
        this.title = ap;
        this.style = an;
        this.className = am;
        this.div = new SEGUtil.Div(0, 0, null, null).get()
    }
    V.prototype = new google.maps.OverlayView();
    V.prototype.onAdd = function() {
        this.div.style.backgroundColor = "white";
        if (this.content) {
            this.div.innerHTML = this.content
        }
        if (this.title) {
            this.div.title = this.title
        }
        if (this.className) {
            this.div.className = this.className
        }
        if (this.style) {
            var an = this.style;
            for (var al in an) {
                this.div.style[al] = an[al]
            }
        }
        var am = this.getPanes();
        am.overlayMouseTarget.appendChild(this.div)
    };
    V.prototype.onRemove = function() {
        this.div.parentNode.removeChild(this.div);
        this.div = null
    };
    V.prototype.draw = function() {
        var al = this.getProjection();
        var am = al.fromLatLngToDivPixel(this.point);
        this.div.style.left = (am.x) + "px";
        this.div.style.top = (am.y) + "px"
    };
    V.prototype.hide = function() {
        this.div.style.display = "none"
    };
    V.prototype.show = function() {
        this.div.style.display = "block"
    };
    V.prototype.updateContent = function(al) {
        this.content = al;
        this.div.innerHTML = al
    };
    V.prototype.updatePosition = function(al) {
        this.point = al;
        this.draw()
    };
    var h = null;
    function a() {
        var al = new google.maps.LatLng(22.553102, 114.110901);
        var am = "webmap_vehicle_hover_info_google";
        h = new V(al, null, null, null, am);
        h.hide();
        h.setMap(G)
    }
    function p(al) {
        var am = SEGUtil.getVehicleMarkerHoverInfo(at.opts,function(html){
        	h.innerHTML = html;
        });
        h.updateContent(am);
        h.updatePosition(al.point);
        h.show()
    }
    function ac() {
        h.hide()
    }
    var v = "white";
    function af(ao) {
        var an = this;
        this.opts = {};
        for (var am in ao) {
            this.opts[am] = ao[am]
        }
        this.div = new SEGUtil.Div(0, 0, null, null).get();
        this.div.style.zIndex = "1";
        var al = 25;
        this.iconDiv = new SEGUtil.Div( - 13, -13, 25, al).get();
        this.numberPlateDiv = new SEGUtil.Div(13, -13, null, al).get();
        this.div.appendChild(this.iconDiv);
        this.div.appendChild(this.numberPlateDiv);
        this.iconDiv.style.cursor = "pointer";
        this.iconDiv.onmouseover = function(au) {
            p(an)
        };
        this.iconDiv.onmouseout = function() {
            ac()
        };
        this.updateIcon();
        this.numberPlateDiv.style.padding = "0 5px";
        this.numberPlateDiv.style.whiteSpace = "nowrap";
        this.numberPlateDiv.style.lineHeight = al + "px";
        this.numberPlateDiv.style.border = "1px solid gray";
        this.numberPlateDiv.style.borderRadius = "2px";
        this.numberPlateDiv.style.background = v;
        if (isIE) {
            this.numberPlateDiv.style.filter = "alpha(opacity=70)"
        } else {
            this.numberPlateDiv.style.opacity = "0.7"
        }
        this.updateNumberPlate();
        var ar = this.opts.lon;
        var aq = this.opts.lat;
        var at = new Converter();
        var ap = at.getEncryPoint(parseFloat(ar), parseFloat(aq));
        this.point = new google.maps.LatLng(ap.y, ap.x)
    }
    af.prototype = new google.maps.OverlayView();
    af.prototype.onAdd = function() {
        var al = this.getPanes();
        al.overlayMouseTarget.appendChild(this.div)
    };
    af.prototype.onRemove = function() {
        this.div.parentNode.removeChild(this.div);
        this.div = null
    };
    af.prototype.draw = function() {
        var al = this.getProjection();
        var am = al.fromLatLngToDivPixel(this.point);
        this.div.style.left = (am.x) + "px";
        this.div.style.top = (am.y) + "px"
    };
    af.prototype.updateNumberPlate = function() {
        this.numberPlateDiv.innerHTML = SEGUtil.parseNull(this.opts.label)
    },
    af.prototype.updateIcon = function() {
        var am = this.opts;
        var al = SEGUtil.getVehicleBackground(am.course, am.speed, am.gpsTime, am.isAlarm, am.status, am.isHistory);
        this.iconDiv.style.background = al
    },
    af.prototype.updatePosition = function() {
        var an = this.opts.lon;
        var am = this.opts.lat;
        var ao = new Converter();
        var al = ao.getEncryPoint(parseFloat(an), parseFloat(am));
        this.point = new google.maps.LatLng(al.y, al.x);
        this.draw()
    },
    af.prototype.update = function(am) {
        for (var al in am) {
            this.opts[al] = am[al]
        }
        this.updateNumberPlate();
        this.updateIcon();
        this.updatePosition()
    };
    af.prototype.flicker = function(ao, ar) {
        var am = ao || 3000;
        var ap = ar || "red";
        var an = v;
        var aq = this.numberPlateDiv;
        var al = setInterval(function() {
            SEGUtil.flickerDiv(aq, an, ap)
        },
        200);
        setTimeout(function() {
            clearInterval(al);
            aq.style.backgroundColor = an
        },
        am)
    };
    function e(an, ar, at, aI) {
        var aC = 0;
        var aA = 0;
        if (typeof(an) == "object") {
            var aH = an;
            var aG = new Converter();
            var az = aG.getEncryPoint(parseFloat(aH.lon), parseFloat(aH.lat));
            var aB = new google.maps.LatLng(az.y, az.x);
            this.point = aB;
            var al = 0;
            var av = 0;
            var ao = 0;
            var am = 0;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.div.style.zIndex = 0;
            if (aH.icon) {
                var aq = aH.icon.url;
                al = aH.icon.width;
                av = aH.icon.height;
                var ap = aH.icon.left || 0;
                var ay = aH.icon.top || 0;
                ao = (typeof(aH.icon.anchorx) == "undefined") ? -(al / 2) : (aH.icon.anchorx);
                am = (typeof(aH.icon.anchory) == "undefined") ? -av: (aH.icon.anchory);
                aC = (typeof(aH.icon.winx) == "undefined") ? 0 : (aH.icon.winx);
                aA = (typeof(aH.icon.winy) == "undefined") ? -av + 1 : (aH.icon.winy);
                this.iconDiv = new SEGUtil.Div(ao, am, al, av).get();
                this.iconDiv.style.background = "url(" + aq + ") " + ap + "px " + ay + "px no-repeat";
                this.iconDiv.style.cursor = "pointer";
                if (aH.title) {
                    this.iconDiv.title = aH.title
                }
                this.div.appendChild(this.iconDiv)
            }
            if (aH.label) {
                var aw = (typeof(aH.label.anchorx) == "undefined") ? al / 2 : aH.label.anchorx;
                var au = (typeof(aH.label.anchory) == "undefined") ? -av: aH.label.anchory;
                var ax = aH.label.text;
                this.labelDiv = new SEGUtil.Div(aw, au, null, null).get();
                this.labelDiv.innerHTML = ax;
                var aF = {
                    whiteSpace: "nowrap",
                    fontSize: "12px"
                };
                var aE = aH.label.style;
                if (aE) {
                    for (var aD in aE) {
                        aF[aD] = aE[aD]
                    }
                }
                for (var aD in aF) {
                    this.labelDiv.style[aD] = aF[aD]
                }
                if (aH.title) {
                    this.labelDiv.title = aH.title
                }
                this.div.appendChild(this.labelDiv)
            }
        } else {
            var aG = new Converter();
            var az = aG.getEncryPoint(parseFloat(an), parseFloat(ar));
            var aB = new google.maps.LatLng(az.y, az.x);
            this.point = aB;
            this.label = at;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.iconDiv = new SEGUtil.Div(0, 0, 12, 20).get();
            this.labelDiv = new SEGUtil.Div(0, 0, null, null).get();
            this.labelDiv.style.border = "1px solid red";
            this.labelDiv.innerHTML = at;
            this.labelDiv.style.fontSize = "12px";
            this.labelDiv.style.padding = "1px";
            this.labelDiv.style.margin = "-20px 0 0 8px";
            this.labelDiv.style.backgroundColor = "white";
            this.labelDiv.style.whiteSpace = "nowrap";
            if (isIE) {
                this.labelDiv.style.filter = "alpha(opacity=65)"
            } else {
                this.labelDiv.style.opacity = "0.65"
            }
            this.iconDiv.style.margin = "-20px 0 0 -6px";
            this.iconDiv.style.background = "url(" + SEGUtil.imageRootDir + "/marker.png) no-repeat";
            this.iconDiv.style.cursor = "pointer";
            if (aI) {
                this.iconDiv.title = aI
            }
            this.div.appendChild(this.iconDiv);
            this.div.appendChild(this.labelDiv)
        }
        this.winx = aC;
        this.winy = aA
    }
    e.prototype = new google.maps.OverlayView();
    e.prototype.onAdd = function() {
        var al = this.getPanes();
        al.overlayMouseTarget.appendChild(this.div)
    };
    e.prototype.onRemove = function() {
        this.div.parentNode.removeChild(this.div);
        this.div = null
    };
    e.prototype.draw = function() {
        var al = this.getProjection();
        var am = al.fromLatLngToDivPixel(this.point);
        this.div.style.left = (am.x) + "px";
        this.div.style.top = (am.y) + "px"
    };
    e.prototype.getZIndex = function() {
        return this.div.style.zIndex
    };
    e.prototype.setZIndex = function(al) {
        this.div.style.zIndex = al
    };
    function c() {
        var at = document.createElement("div");
        at.style.marginRight = "5px";
        at.style.marginTop = "6px";
        if (isIE) {
            at.style.marginTop = "2px"
        }
        var an = document.createElement("div");
        an.style.backgroundColor = "white";
        an.style.border = "1px solid #666";
        an.style.borderRadius = "2px";
        an.style.cursor = "pointer";
        an.style.textAlign = "center";
        at.appendChild(an);
        var am = document.createElement("div");
        am.style.fontFamily = "Arial,sans-serif";
        am.style.fontSize = "12px";
        am.style.paddingLeft = "4px";
        am.style.paddingRight = "4px";
        am.innerHTML = $tfinf;
        an.appendChild(am);
        var al = false;
        var ap = null;
        var ar = null;
        var aq = this;
        google.maps.event.addDomListener(an, "click",
        function() {
            if (ap == null) {
                ap = new google.maps.TrafficLayer()
            }
            if (ar == null) {
                ar = new aa().getDiv()
            }
            if (!al) {
                al = true;
                an.style.borderWidth = "2px";
                ap.setMap(G);
                G.controls[google.maps.ControlPosition.RIGHT_TOP].push(ar)
            } else {
                aq.hideTrafficInfo()
            }
        });
        function ao(au, aw) {
            for (var av = 0; av < au.getLength(); av++) {
                if (au.getAt(av) == aw) {
                    au.removeAt(av);
                    return
                }
            }
        }
        this.index = 1;
        this.hideTrafficInfo = function() {
            al = false;
            an.style.borderWidth = "1px";
            ap.setMap(null);
            ao(G.controls[google.maps.ControlPosition.RIGHT_TOP], ar)
        };
        this.getDiv = function() {
            return at
        }
    }
    function aa() {
        var am = document.createElement("div");
        am.style.marginTop = "2px";
        am.style.position = "absolute";
        am.style.border = "1px solid #000";
        am.style.borderRadius = "2px";
        am.style.background = "#FFFFFF";
        am.style.width = "150px";
        am.style.height = "40px";
        var ao = document.createElement("div");
        ao.style.position = "absolute";
        ao.style.left = "133px";
        ao.style.top = "3px";
        ao.style.cursor = "pointer";
        ao.style.width = "12px";
        ao.style.height = "12px";
        ao.style.background = "url(" + SEGUtil.imageRootDir + "/popup_close.gif) no-repeat";
        ao.onclick = function() {
            if (z != null) {
                z.hideTrafficInfo()
            }
        };
        var au = document.createElement("div");
        au.style.position = "absolute";
        au.style.left = "10px";
        au.style.top = "5px";
        au.style.width = "30px";
        au.innerHTML = $tfinfc;
        var ap = document.createElement("div");
        ap.style.position = "absolute";
        ap.style.left = "100px";
        ap.style.top = "5px";
        ap.style.width = "30px";
        ap.innerHTML = $tfinfu;
        var an = document.createElement("div");
        an.style.position = "absolute";
        an.style.left = "20px";
        an.style.top = "25px";
        an.style.height = "8px";
        an.style.width = "18px";
        an.style.background = "#000";
        an.style.border = "1px solid gray";
        var aq = document.createElement("div");
        aq.style.position = "absolute";
        aq.style.left = "5px";
        aq.style.top = "0px";
        aq.style.height = "6px";
        aq.style.width = "6px";
        aq.style.background = "#990000";
        an.appendChild(aq);
        var al = document.createElement("div");
        al.style.position = "absolute";
        al.style.left = "45px";
        al.style.top = "25px";
        al.style.height = "8px";
        al.style.width = "18px";
        al.style.background = "#990000";
        al.style.border = "1px solid gray";
        var ar = document.createElement("div");
        ar.style.position = "absolute";
        ar.style.left = "70px";
        ar.style.top = "25px";
        ar.style.height = "8px";
        ar.style.width = "18px";
        ar.style.background = "#fc0";
        ar.style.border = "1px solid gray";
        var at = document.createElement("div");
        at.style.position = "absolute";
        at.style.left = "95px";
        at.style.top = "25px";
        at.style.height = "8px";
        at.style.width = "18px";
        at.style.background = "#30b100";
        at.style.border = "1px solid gray";
        am.appendChild(ao);
        am.appendChild(au);
        am.appendChild(ap);
        am.appendChild(an);
        am.appendChild(al);
        am.appendChild(ar);
        am.appendChild(at);
        this.getDiv = function() {
            return am
        }
    }
    function U(am, al) {
        if (typeof(google.maps.geometry) == "undefined") {
            return 0
        }
        return google.maps.geometry.spherical.computeDistanceBetween(am, al)
    }
    function ab(al) {
        if (typeof(google.maps.geometry) == "undefined") {
            return ["0", $cb]
        }
        var an = al.getPath();
        var am = google.maps.geometry.spherical.computeLength(an);
        return SEGUtil.getDistanceDesc(am)
    }
    var m = null;
    var i = null;
    var s = null;
    var u = null;
    this.setHistoryData = function(am, al) {
        m = am;
        i = al
    };
    this.resetHistory = function() {
        P();
        i = null;
        m = null
    };
    function P() {
        if (s != null) {
            s.setMap(null);
            s = null
        }
        if (u != null) {
            u.setMap(null);
            u = null
        }
    }
    this.playHistoryTo = function(ao) {
        if (s == null) {
            var at = [];
            for (var an = 0; an < i.length; an++) {
                var ar = i[an];
                var ap = new Converter();
                var am = ap.getEncryPoint(parseFloat(ar.lon), parseFloat(ar.lat));
                var aq = new google.maps.LatLng(am.y, am.x);
                at.push(aq)
            }
            var au = {
                path: at,
                clickable: false,
                draggable: false,
                editable: false,
                visible: true,
                strokeColor: "blue",
                strokeOpacity: 1,
                strokeWeight: 2
            };
            s = new google.maps.Polyline(au);
            s.setMap(G)
        }
        if (ao == -1) {
            if (u != null) {
                u.setMap(null);
                u = null
            }
            return
        }
        if (ao < 0 || ao >= i.length) {
            return
        }
        var al = i[ao];
        if (u == null) {
            for (var an in m) {
                al[an] = m[an]
            }
            u = new af(al);
            u.setMap(G)
        } else {
            u.update(al)
        }
        var aq = u.point;
        if (!G.getBounds().contains(aq)) {
            G.setCenter(aq)
        }
    };
    this.getLocation = function(ap, ao, ar) {
        var aq = new Converter();
        var an = aq.getEncryPoint(parseFloat(ap), parseFloat(ao));
        var al = new google.maps.LatLng(an.y, an.x);
        var am = new google.maps.Geocoder();
        am.geocode({
            location: al
        },
        function(av, au) {
            if (au == google.maps.GeocoderStatus.OK) {
                if (!av || av.length == 0) {
                    ar("");
                    return
                }
                var at = av[0].formatted_address;
                var aw = at.indexOf("邮政编码");
                if (aw != -1) {
                    at = at.substring(0, aw)
                }
                ar(at)
            } else {
                ar("")
            }
        })
    }
};
var mapbarLatLonUtil = {
    encrypt: function(a, b) {
        a = parseFloat(a) * 100000 % 36000000;
        b = parseFloat(b) * 100000 % 36000000;
        _X = parseInt(((Math.cos(b / 100000)) * (a / 18000)) + ((Math.sin(a / 100000)) * (b / 9000)) + a);
        _Y = parseInt(((Math.sin(b / 100000)) * (a / 18000)) + ((Math.cos(a / 100000)) * (b / 9000)) + b);
        return [_X / 100000, _Y / 100000]
    },
    decrypt: function(a, b) {
        a = parseFloat(a) * 100000 % 36000000;
        b = parseFloat(b) * 100000 % 36000000;
        x1 = parseInt( - (((Math.cos(b / 100000)) * (a / 18000)) + ((Math.sin(a / 100000)) * (b / 9000))) + a);
        y1 = parseInt( - (((Math.sin(b / 100000)) * (a / 18000)) + ((Math.cos(a / 100000)) * (b / 9000))) + b);
        x2 = parseInt( - (((Math.cos(y1 / 100000)) * (x1 / 18000)) + ((Math.sin(x1 / 100000)) * (y1 / 9000))) + a + ((a > 0) ? 1 : -1));
        y2 = parseInt( - (((Math.sin(y1 / 100000)) * (x1 / 18000)) + ((Math.cos(x1 / 100000)) * (y1 / 9000))) + b + ((b > 0) ? 1 : -1));
        return [x2 / 100000, y2 / 100000]
    }
};
var maplet = null;
var apiType = 1;
var DISABLE_DBCLICK_ZOOM = false;
var MapbarMapProxy = function(ak, ao) {
    var d = this;
    var ar = ao;
    var ac = document.getElementById(ak);
    var M = "mapbar_maplet_div";
    var Y = document.createElement("div");
    Y.id = M;
    Y.style.position = "absolute";
    Y.style.left = "0";
    Y.style.top = "0";
    Y.style.width = ac.offsetWidth + "px";
    Y.style.height = ac.offsetHeight + "px";
    ac.appendChild(Y);
    var ae = new Maplet(M);
    maplet = ae;
    ae.centerAndZoom(new MPoint(114.110901, 22.553102), 12);
    var A = [];
    var U = [];
    var k = [];
    var ab = [A, U];
    this.init = function() {
        ae.addControl(new MStandardControl());
        ae.clickToCenter = false;
        ae.setIwStdSize(260, 200);
        ae.setCursorStyle("default", SEGUtil.imageRootDir + "/openhand.cur");
        ae.setCursorStyle("move", SEGUtil.imageRootDir + "/closedhand.cur");
        ae.setCursorStyle("crosshair", SEGUtil.imageRootDir + "/cross_r.cur");
        i();
        a();
        c()
    };
    var b = null;
    function B(ax) {
        var ay = {
            whiteSpace: "nowrap",
            padding: "5px 2px 2px 5px",
            margin: "-20px 0 0 -6px",
            cursor: "pointer",
            border: "1px solid #6482B9"
        };
        if (isIE) {
            ay.filter = "alpha(opacity=80)"
        } else {
            ay.opacity = "0.8"
        }
        b = new al(8, ax, "删除标注", ay);
        ae.addPanel(b.panel);
        b.triggerMarker = null;
        E(b.div, "click",
        function() {
            ar.deleteStaticMarker(b.triggerMarker);
            b.hide()
        });
        aq(document, "click",
        function() {
            if (b != null) {
                b.hide()
            }
        })
    }
    this.destroyMap = function() {
        w()
    };
    this.setCenter = function(aB, aA) {
        var az = mapbarLatLonUtil.encrypt(parseFloat(aB), parseFloat(aA));
        var ay = az[0];
        var ax = az[1];
        ae.setCenter(new MPoint(ay, ax))
    };
    this.centerAndZoom = function(aB, aA, aC) {
        var az = mapbarLatLonUtil.encrypt(parseFloat(aB), parseFloat(aA));
        var ay = az[0];
        var ax = az[1];
        ae.centerAndZoom(new MPoint(ay, ax), aC)
    };
    this.getCenter = function() {
        var ax = ae.getCenter();
        var ay = mapbarLatLonUtil.decrypt(ax.lon, ax.lat);
        return {
            lon: ay[0],
            lat: ay[1]
        }
    };
    this.isPointInView = function(ay, ax) {
        return f(ae, ay, ax)
    };
    this.getZoom = function() {
        return ae.getZoomLevel()
    };
    this.fitBounds = function(aE, az, aC, ax) {
        var aD = mapbarLatLonUtil.encrypt(parseFloat(aE), parseFloat(az));
        var aB = mapbarLatLonUtil.encrypt(parseFloat(aC), parseFloat(ax));
        var aA = SEGUtil.sortPoint(aD[0], aD[1], aB[0], aB[1]);
        var ay = new MPoint(aA[0], aA[1]);
        var aF = new MPoint(aA[2], aA[3]);
        ae.setAutoZoom(ay, aF)
    };
    this.openDistanceTool = function() {
        u("rule")
    };
    this.openCoordTool = function() {
        u("coord")
    };
    this.drawPoint = function(ax) {
        ad = ax;
        u("point")
    };
    this.drawCircle = function(ax) {
        ad = ax;
        u("circle")
    };
    this.drawRectangle = function(ax) {
        ad = ax;
        u("rect")
    };
    this.drawPolygon = function(ax) {
        ad = ax;
        u("polygon")
    };
    this.drawPolyline = function(ax) {
        ad = ax;
        u("polyline")
    };
    this.resize = function() {
        var ay = ac.offsetWidth;
        var ax = ac.offsetHeight;
        Y.style.width = ay + "px";
        Y.style.height = ax + "px";
        ae.resize(ay, ax)
    };
    this.newSimpleMarker = function(aD, aB, aA, aC, aE) {
        var ay = new SEGSimpleMarker();
        var ax = new m(aD, aB, aA, aC);
        ay.target = ax;
        if (typeof(aD) == "object") {
            var az = aD;
            ay.config = az;
            ay.lon = az.lon;
            ay.lat = az.lat;
            ay.label = az.label;
            ay.title = az.title;
            ay.id = az.id
        } else {
            ay.lon = aD;
            ay.lat = aB;
            ay.label = aA;
            ay.title = aC;
            ay.id = aE
        }
        return ay
    };
    this.setSimpleMarkerIcon = function(aI, ay) {
        if (!this.isMarkerOnMap(aI)) {
            return
        }
        var az = ay.url;
        var aA = ay.width;
        var aH = ay.height;
        var aC = ay.left || 0;
        var aG = ay.top || 0;
        var aF = (typeof(ay.anchorx) == "undefined") ? -(aA / 2) : (ay.anchorx);
        var aE = (typeof(ay.anchory) == "undefined") ? -aH: (ay.anchory);
        var aD = (typeof(ay.winx) == "undefined") ? 0 : (ay.winx);
        var aB = (typeof(ay.winy) == "undefined") ? -aH + 1 : (ay.winy);
        var ax = aI.target.iconDiv;
        ax.style.width = aA + "px";
        ax.style.height = aH + "px";
        ax.style.background = "url(" + az + ") " + aC + "px " + aG + "px no-repeat";
        ax.style.left = aF + "px";
        ax.style.top = aE + "px";
        aI.target.winx = aD;
        aI.target.winy = aB
    };
    this.toTop = function(ax, ay) {
        ax.target.setZIndex(ay ? 5 : 4)
    };
    var af = [];
    this.newInfoWindow = function(aC, aA, az, ax, aD) {
        var aB = new H(aC, aA, az, ax);
        var ay = new SEGInfoWindow();
        ay.target = aB;
        af.push(ay);
        return ay
    };
    this.showInfoWindow = function(aU, az) {
        az.target.divPoint.style.left = aU.target.winx + "px";
        az.target.divPoint.style.top = aU.target.winy + "px";
        az.target.panel.setLocation({
            type: "latlon",
            x: 0,
            y: 0,
            pt: aU.target.point
        });
        if (!az.target.isShow) {
            ae.addPanel(az.target.panel);
            az.target.isShow = true
        }
        var a3 = aU.target;
        var a2 = az.target;
        var aH = ae.toScreenCoordinate(a3.point.lon + "," + a3.point.lat);
        var aC = 70;
        var a6 = 10;
        var a1 = 10;
        var aV = 10;
        var aM = aH[0] + a2.divWindowLeft - aC;
        var aB = aH[0] + a2.divWindowLeft + a2.width + a6;
        var aL = aH[1] + a2.divWindowTop + a3.winy - a1;
        var aA = aH[1] + aV;
        var aQ = 0;
        var aO = 0;
        if (aM < 0) {
            aQ += aM
        } else {
            if (aB > ae.width) {
                aQ += (aB - ae.width)
            }
        }
        if (aL < 0) {
            aO += aL
        } else {
            if (aA > ae.height) {
                aO += (aA - ae.height)
            }
        }
        var a4 = ae.overview;
        var aT = a4.getRect();
        if (aT.min.x != 0 || aT.min.y != 0) {
            var aI = aH[0] - aQ;
            var aG = aH[1] - aO;
            var aE = aB - aQ;
            var aD = aA + a3.winy - 30 - aO;
            var a0 = 0;
            var aZ = 0;
            var aK = 0;
            var aJ = 0;
            if (aI > aT.min.x && aG > aT.min.y) {
                var a0 = aI - aT.min.x;
                var aK = aG - aT.min.y
            }
            if (aE > aT.min.x && aD > aT.min.y) {
                var aZ = aE - aT.min.x;
                var aJ = aD - aT.min.y
            }
            var a5 = Math.max(a0, aZ);
            var aW = Math.max(aK, aJ);
            var aF = a0 + aJ;
            var ax = aZ + aK;
            if (a5 <= aW && a5 <= aF && a5 <= ax) {
                aQ += a5
            } else {
                if (aW <= a5 && aW <= aF && aW <= ax) {
                    aO += aW
                } else {
                    if (aF <= a5 && aF <= aW && aF <= ax) {
                        aQ += a0;
                        aO += aJ
                    } else {
                        if (ax <= a5 && ax <= aW && ax <= aF) {
                            aQ += aZ;
                            aO += aK
                        }
                    }
                }
            }
        }
        if (aQ != 0 || aO != 0) {
            var ay = ae.getCenter();
            var aR = ae.toScreenCoordinate(ay.lon + "," + ay.lat);
            var aY = aR[0] + aQ;
            var aX = aR[1] + aO;
            var aP = ae.toMapCoordinate(aY, aX);
            var aN = aP.split(",");
            var aS = new MPoint(parseFloat(aN[0]), parseFloat(aN[1]));
            ae.setCenter(aS)
        }
    };
    this.setInfoWindowTitle = function(ax, ay) {
        ax.target.titleDiv.innerHTML = ay
    };
    this.isInfoWindowExist = function(ay) {
        var ax = SEGUtil.indexOfArray(af, ay);
        return ax != -1
    };
    this.closeInfoWindow = function(ax) {
        if (!this.isInfoWindowExist(ax)) {
            return
        }
        ae.removePanel(ax.target.panel, false);
        ax.target.isShow = false
    };
    this.closeAllInfoWindow = function() {
        for (var ax = 0; ax < af.length; ax++) {
            var ay = af[ax].target;
            ae.removePanel(ay.panel, false);
            ay.isShow = false
        }
    };
    this.addEventListener = function(az, ax, ay) {
        if (SEGSimpleMarker.prototype.isPrototypeOf(az)) {
            SEGUtil.addEventForDom(az.target.div, ax, ay)
        }
    };
    this.newVehicleMarker = function(az) {
        var ax = new Q(az);
        var ay = new SEGVehicleMarker();
        ay.target = ax;
        ay.opts = az;
        return ay
    };
    this.newCircle = function(ay, aJ, aE, az, aN, aC, aF, aA, aM) {
        var aD = mapbarLatLonUtil.encrypt(parseFloat(ay), parseFloat(aJ));
        var aL = new MPoint(aD[0], aD[1]);
        var aK = typeof(aC) == "undefined" ? 2 : aC;
        var aB = typeof(aF) == "undefined" ? 1 : aF;
        var aI = typeof(aM) == "undefined" ? 0.65 : aM;
        var aH = new MBrush();
        aH.arrow = false;
        aH.fill = true;
        aH.color = aN || "blue";
        aH.stroke = aK;
        aH.transparency = aB * 100;
        aH.bgcolor = aA || "white";
        aH.bgtransparency = aI * 100;
        var ax = new MEllipse(aL, aE, null, aH);
        var aG = new SEGCircle();
        aG.target = ax;
        aG.lon = ay;
        aG.lat = aJ;
        aG.radius = aE;
        aG.id = az;
        aG.strokeColor = aN;
        aG.strokeWeight = aC;
        aG.strokeOpacity = aF;
        aG.fillColor = aA;
        aG.fillOpacity = aM;
        return aG
    };
    this.newRectangle = function(aO, aA, aN, ay, aH, aI, aL, az, aD, aJ) {
        var aC = mapbarLatLonUtil.encrypt(parseFloat(aO), parseFloat(aA));
        var aB = mapbarLatLonUtil.encrypt(parseFloat(aN), parseFloat(ay));
        var aG = typeof(aL) == "undefined" ? 2 : aL;
        var aK = typeof(az) == "undefined" ? 1 : az;
        var aE = typeof(aJ) == "undefined" ? 0.65 : aJ;
        var ax = new MBrush();
        ax.arrow = false;
        ax.fill = true;
        ax.color = aI || "blue";
        ax.stroke = aG;
        ax.transparency = aK * 100;
        ax.bgcolor = aD || "white";
        ax.bgtransparency = aE * 100;
        var aF = au(aC[0], aC[1], aB[0], aB[1]);
        var aP = new MPolyline(aF, ax);
        var aM = new SEGRectangle();
        aM.target = aP;
        aM.lon1 = aO;
        aM.lat1 = aA;
        aM.lon2 = aN;
        aM.lat2 = ay;
        aM.id = aH;
        aM.strokeColor = aI;
        aM.strokeWeight = aL;
        aM.strokeOpacity = az;
        aM.fillColor = aD;
        aM.fillOpacity = aJ;
        return aM
    };
    function au(aB, ay, aA, ax) {
        var az = SEGUtil.sortPoint(aB, ay, aA, ax);
        var aF = new MPoint(az[0], az[3]);
        var aE = new MPoint(az[0], az[1]);
        var aD = new MPoint(az[2], az[1]);
        var aC = new MPoint(az[2], az[3]);
        return [aF, aE, aD, aC]
    }
    this.newPolygon = function(aI, aG, aH, aN, az, aA, aK) {
        var aE = [];
        for (var aL = 0; aL < aI.length; aL++) {
            var aO = aI[aL];
            var aB = mapbarLatLonUtil.encrypt(parseFloat(aO.lon), parseFloat(aO.lat));
            var aJ = new MPoint(aB[0], aB[1]);
            aE.push(aJ)
        }
        var aF = typeof(aN) == "undefined" ? 2 : aN;
        var aM = typeof(az) == "undefined" ? 1 : az;
        var aC = typeof(aK) == "undefined" ? 0.65 : aK;
        var ay = new MBrush();
        ay.arrow = false;
        ay.fill = true;
        ay.color = aH || "blue";
        ay.stroke = aF;
        ay.transparency = aM * 100;
        ay.bgcolor = aA || "white";
        ay.bgtransparency = aC * 100;
        var aD = new MPolyline(aE, ay);
        var ax = new SEGPolygon();
        ax.target = aD;
        ax.ps = aI;
        ax.id = aG;
        ax.strokeColor = aH;
        ax.strokeWeight = aN;
        ax.strokeOpacity = az;
        ax.fillColor = aA;
        ax.fillOpacity = aK;
        return ax
    };
    this.newPolyline = function(ax, ay, aL, aB, aE) {
        var aK = [];
        for (var aA = 0; aA < ax.length; aA++) {
            var aH = ax[aA];
            var aD = mapbarLatLonUtil.encrypt(parseFloat(aH.lon), parseFloat(aH.lat));
            var aJ = new MPoint(aD[0], aD[1]);
            aK.push(aJ)
        }
        var aI = typeof(aB) == "undefined" ? 2 : aB;
        var az = typeof(aE) == "undefined" ? 1 : aE;
        var aF = new MBrush();
        aF.arrow = false;
        aF.fill = false;
        aF.color = aL || "blue";
        aF.stroke = aI;
        aF.transparency = az * 100;
        var aG = new MPolyline(aK, aF);
        var aC = new SEGPolyline();
        aC.target = aG;
        aC.ps = ax;
        aC.id = ay;
        aC.strokeColor = aL;
        aC.strokeWeight = aB;
        aC.strokeOpacity = aE;
        return aC
    };
    this.addMarker = function(ax, ay) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(ax)) {
            ae.addPanel(ax.target.panel);
            k.push(ax);
            return
        }
        var az = null;
        if (typeof(ay) == "undefined" || ay == 0) {
            az = A
        } else {
            if (ay == 1) {
                az = U
            }
        }
        if (az) {
            if (m.prototype.isPrototypeOf(ax.target)) {
                ae.addPanel(ax.target.panel)
            } else {
                ae.addOverlay(ax.target)
            }
            az.push(ax)
        }
        if (typeof(ay) != "undefined" && ay == 1 && SEGSimpleMarker.prototype.isPrototypeOf(ax)) {
            E(ax.target.iconDiv, "contextmenu",
            function(aA) {
                if (b == null) {
                    B(ax.target.panel.options.location.pt)
                } else {
                    b.updatePosition(ax.target.panel.options.location.pt);
                    b.show()
                }
                b.triggerMarker = ax
            })
        }
    };
    this.copyMarker = function(ax) {
        switch (ax.markerType) {
        case 1:
            if (ax.config) {
                return this.newSimpleMarker(ax.config)
            }
            return this.newSimpleMarker(ax.lon, ax.lat, ax.label, ax.title, ax.id);
        case 2:
            return this.newVehicleMarker(ax.opts);
        case 3:
            return this.newCircle(ax.lon, ax.lat, ax.radius, ax.id, ax.strokeColor, ax.strokeWeight, ax.strokeOpacity, ax.fillColor, ax.fillOpacity);
        case 4:
            return this.newRectangle(ax.lon1, ax.lat1, ax.lon2, ax.lat2, ax.id, ax.strokeColor, ax.strokeWeight, ax.strokeOpacity, ax.fillColor, ax.fillOpacity);
        case 5:
            return this.newPolygon(ax.ps, ax.id, ax.strokeColor, ax.strokeWeight, ax.strokeOpacity, ax.fillColor, ax.fillOpacity);
        case 6:
            return this.newPolyline(ax.ps, ax.id, ax.strokeColor, ax.strokeWeight, ax.strokeOpacity);
        default:
            return null
        }
    };
    this.isMarkerOnMap = function(ax) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(ax)) {
            for (var az = 0; az < k.length; az++) {
                if (k[az] == ax) {
                    return true
                }
            }
        } else {
            for (var az = 0; az < ab.length; az++) {
                var aA = ab[az];
                for (var ay = 0; ay < aA.length; ay++) {
                    if (aA[ay] == ax) {
                        return true
                    }
                }
            }
        }
        return false
    };
    function q(ay) {
        for (var ax = 0; ax < k.length; ax++) {
            if (k[ax].opts.id == ay) {
                return k[ax]
            }
        }
        return null
    }
    this.addOrUpdateVehicleMarkerById = function(ay) {
        var aA = ay.id;
        var ax = q(aA);
        if (ax != null) {
            ax.target.update(ay);
            return ax
        }
        var az = this.newVehicleMarker(ay);
        this.addMarker(az);
        return az
    };
    this.removeMarker = function(ax) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(ax)) {
            for (var az = 0; az < k.length; az++) {
                if (k[az] == ax) {
                    ae.removePanel(ax.target.panel, true);
                    k.splice(az, 1);
                    return true
                }
            }
        } else {
            for (var az = 0; az < ab.length; az++) {
                var aA = ab[az];
                for (var ay = 0; ay < aA.length; ay++) {
                    if (aA[ay] == ax) {
                        if (m.prototype.isPrototypeOf(ax.target)) {
                            ae.removePanel(ax.target.panel, true)
                        } else {
                            ae.removeOverlay(ax.target)
                        }
                        aA.splice(ay, 1);
                        return true
                    }
                }
            }
        }
        return false
    };
    this.clearNonStaticMarkers = function() {
        for (var ax = 0; ax < A.length; ax++) {
            var ay = A[ax].target;
            if (m.prototype.isPrototypeOf(ay)) {
                ae.removePanel(ay.panel, true)
            } else {
                ae.removeOverlay(ay)
            }
        }
        A.splice(0, A.length)
    };
    this.clearStaticMarkers = function() {
        for (var ax = 0; ax < U.length; ax++) {
            var ay = U[ax].target;
            if (m.prototype.isPrototypeOf(ay)) {
                ae.removePanel(ay.panel, true)
            } else {
                ae.removeOverlay(ay)
            }
        }
        U.splice(0, U.length)
    };
    this.clearVehicleMarkers = function() {
        for (var ax = 0; ax < k.length; ax++) {
            ae.removePanel(k[ax].target.panel, true)
        }
        k.splice(0, k.length)
    };
    this.getNonStaticMarkers = function() {
        return A
    };
    this.getStaticMarkers = function() {
        return U
    };
    this.getVehicleMarkers = function() {
        return k
    };
    var P = false;
    function c() {
        aq(document, "mouseup",
        function() {
            P = false;
            ah()
        })
    }
    var an = [];
    function V(aA, ay, aB) {
        for (var az = 0; az < an.length; az++) {
            var ax = an[az];
            if (ax.dom == aA && ax.eventName == ay && ax.tempFunc == aB) {
                return ax.realFunc
            }
        }
        return null
    }
    function w() {
        for (var az = 0; az < an.length; az++) {
            var ax = an[az];
            var aB = ax.dom;
            var ay = ax.eventName;
            var aA = ax.realFunc;
            if (aB.removeEventListener) {
                aB.removeEventListener(ay, aA, false)
            } else {
                if (aB.detachEvent) {
                    aB.detachEvent("on" + ay, aA)
                }
            }
        }
    }
    function E(az, ax, aA) {
        var ay = function(aB) {
            aw(aB, aA)
        };
        SEGUtil.addEventForDom(az, ax, ay)
    }
    function aq(aA, ay, aB) {
        var az = function(aC) {
            aw(aC, aB)
        };
        if (document.addEventListener) {
            aA.addEventListener(ay, az, false)
        } else {
            if (document.attachEvent) {
                aA.attachEvent("on" + ay, az)
            }
        }
        var ax = {
            dom: aA,
            eventName: ay,
            tempFunc: aB,
            realFunc: az
        };
        an.push(ax)
    }
    var aw = function(ay, aD) {
        var aB = window.event || ay;
        var aA = aB.target || aB.srcElement;
        if (!aA) {
            aD({
                event: aB
            });
            return
        }
        var ax = aB.offsetX || aB.layerX || 0;
        var aC = aB.offsetY || aB.layerY || 0;
        if (aA.nodeType != 1) {
            aA = aA.parentNode
        }
        while (aA && aA != Y) {
            if (! (aA.clientWidth == 0 && aA.clientHeight == 0 && aA.offsetParent && aA.offsetParent.nodeName == "TD")) {
                ax += aA.offsetLeft || 0;
                aC += aA.offsetTop || 0
            }
            aA = aA.offsetParent
        }
        var az = {
            event: aB,
            x: ax,
            y: aC
        };
        aD(az)
    };
    var n = null;
    var ad = null;
    var l = null;
    var x = null;
    var av = null;
    var O = null;
    var L = null;
    function G() {
        x = null;
        av = null;
        O = null;
        L = null
    }
    function am(ax) {
        ae.removeOverlay(ax[0]);
        for (var ay = 1; ay < ax.length; ay++) {
            ae.removePanel(ax[ay], true)
        }
    }
    var t = null;
    var g = null;
    var R = null;
    function s() {
        t = null;
        g = null;
        R = null
    }
    var ag = null;
    var h = null;
    var C = null;
    function W() {
        ag = null;
        h = null;
        C = null
    }
    var ap = null;
    var at = null;
    var Z = null;
    function N() {
        ap = null;
        at = null;
        Z = null
    }
    var z = null;
    var F = null;
    var D = null;
    function o() {
        z = null;
        F = null;
        D = null
    }
    var j = null;
    var S = null;
    function T() {
        S = document.createElement("div");
        S.style.zIndex = 2;
        S.style.position = "absolute";
        S.style.left = 0;
        S.style.top = 0;
        S.style.width = "100%";
        S.style.height = "100%";
        Y.appendChild(S)
    }
    function ah() {
        if (S != null) {
            Y.removeChild(S);
            S = null
        }
    }
    var u = function(ay, ax) {
        n = ay;
        switch (n) {
        case "pan":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/openhand.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/closedhand.cur");
            setTimeout(function() {
                DISABLE_DBCLICK_ZOOM = false
            },
            200);
            break;
        case "point":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/cross_r.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/cross_r.cur");
            break;
        case "circle":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/cross_r.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/cross_r.cur");
            T();
            break;
        case "rect":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/cross_r.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/cross_r.cur");
            T();
            break;
        case "polygon":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/cross_r.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/cross_r.cur");
            DISABLE_DBCLICK_ZOOM = true;
            break;
        case "polyline":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/cross_r.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/cross_r.cur");
            DISABLE_DBCLICK_ZOOM = true;
            break;
        case "rule":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/ruler.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/ruler.cur");
            DISABLE_DBCLICK_ZOOM = true;
            break;
        case "coord":
            ae.setCursorStyle("default", SEGUtil.imageRootDir + "/cross_r.cur");
            ae.setCursorStyle("move", SEGUtil.imageRootDir + "/cross_r.cur");
            DISABLE_DBCLICK_ZOOM = true;
            break;
        default:
            break
        }
    };
    function a() {
        var aA = {
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -6px",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.png) no-repeat -25px -312px"
        };
        var az = {
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -20px",
            cursor: "pointer",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.gif) no-repeat 0px -14px"
        };
        var aB = {
            whiteSpace: "nowrap",
            color: "#333333",
            height: "20px",
            border: "1px solid #333333",
            margin: "-6px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var aC = {
            whiteSpace: "nowrap",
            width: "120px",
            border: "1px solid red",
            margin: "20px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var ay = {
            whiteSpace: "nowrap",
            width: "140px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        var ax = {
            whiteSpace: "nowrap",
            width: "160px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        if (isIE) {
            aB.filter = "alpha(opacity=70)";
            aC.filter = "alpha(opacity=70)"
        } else {
            aB.opacity = "0.7";
            aC.opacity = "0.7"
        }
        E(Y, "click",
        function(aI) {
            var aJ = aj(aI);
            switch (n) {
            case "point":
                u("pan");
                if (j != null) {
                    ae.removePanel(j.panel, true);
                    j = null
                }
                if (ad) {
                    var aD = mapbarLatLonUtil.decrypt(aJ.lon, aJ.lat);
                    ad(aD[0], aD[1])
                }
                break;
            case "polygon":
                if (at == null) {
                    var aH = new MBrush();
                    aH.arrow = false;
                    aH.fill = true;
                    aH.color = "red";
                    aH.stroke = 2;
                    aH.transparency = 100;
                    aH.bgcolor = "white";
                    aH.bgtransparency = 65;
                    at = new MPolyline([aJ], aH);
                    ae.addOverlay(at)
                } else {
                    var aL = at.pts;
                    if (Z != null) {
                        aL.pop();
                        Z = null
                    }
                    aL.push(aJ);
                    at.setPoints(aL)
                }
                break;
            case "polyline":
                if (F == null) {
                    var aH = new MBrush();
                    aH.arrow = false;
                    aH.fill = false;
                    aH.color = "red";
                    aH.stroke = 2;
                    aH.transparency = 100;
                    F = new MPolyline([aJ], aH);
                    ae.addOverlay(F)
                } else {
                    var aL = F.pts;
                    if (D != null) {
                        aL.pop();
                        D = null
                    }
                    aL.push(aJ);
                    F.setPoints(aL)
                }
                break;
            case "rule":
                if (x == null) {
                    av = [];
                    var aH = new MBrush();
                    aH.arrow = false;
                    aH.fill = false;
                    aH.color = "red";
                    aH.stroke = 2;
                    aH.transparency = 60;
                    x = new MPolyline([aJ], aH);
                    ae.addOverlay(x);
                    var aK = new al(4, aJ, $sp, aB);
                    ae.addPanel(aK.panel);
                    av.push(x);
                    av.push(aK.panel)
                } else {
                    var aG = x.pts;
                    if (L != null) {
                        aG.pop();
                        L = null
                    }
                    aG.push(aJ);
                    x.setPoints(aG);
                    var aF = e(x);
                    var aK = new al(4, aJ, aF[0] + aF[1], aB);
                    ae.addPanel(aK.panel);
                    av.push(aK.panel)
                }
                var aE = new al(5, aJ, null, aA);
                ae.addPanel(aE.panel);
                av.push(aE.panel);
                break;
            default:
                break
            }
        });
        E(Y, "mousemove",
        function(aH) {
            var aK = aj(aH);
            switch (n) {
            case "point":
                var aE = mapbarLatLonUtil.decrypt(aK.lon, aK.lat);
                var aD = "<span>" + aE[0] + ", " + aE[1] + "</span><div style='color:#808080'>" + $ctsp + "</div>";
                if (j == null) {
                    j = new al(7, aK, aD, ay);
                    ae.addPanel(j.panel)
                } else {
                    j.updatePosition(aK);
                    j.updateContent(aD)
                }
                break;
            case "circle":
                if (g == null) {
                    g = new al(7, aK, $mdtscc, ay);
                    ae.addPanel(g.panel)
                } else {
                    g.updatePosition(aK)
                }
                if (R != null) {
                    g.updateContent($ctc);
                    var aO = y(t, aK);
                    R.sax = aO;
                    R.update()
                }
                break;
            case "rect":
                if (h == null) {
                    h = new al(7, aK, $mdtsrs, ay);
                    ae.addPanel(h.panel)
                } else {
                    h.updatePosition(aK)
                }
                if (C != null) {
                    h.updateContent($ctc);
                    var aF = SEGUtil.sortPoint(ag.lon, ag.lat, aK.lon, aK.lat);
                    var aM = new MPoint(aF[0], aF[3]);
                    var aL = new MPoint(aF[0], aF[1]);
                    var aJ = new MPoint(aF[2], aF[1]);
                    var aI = new MPoint(aF[2], aF[3]);
                    C.setPoints([aM, aL, aJ, aI])
                }
                break;
            case "polygon":
                if (ap == null) {
                    var aD = "<span>" + $ctspgp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    ap = new al(7, aK, aD, ax);
                    ae.addPanel(ap.panel)
                } else {
                    ap.updatePosition(aK)
                }
                if (at != null) {
                    var aN = at.pts;
                    if (Z != null) {
                        aN.pop();
                        Z = null
                    }
                    Z = aK;
                    aN.push(Z);
                    at.setPoints(aN)
                }
                break;
            case "polyline":
                if (z == null) {
                    var aD = "<span>" + $ctsplp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    z = new al(7, aK, aD, ax);
                    ae.addPanel(z.panel)
                } else {
                    z.updatePosition(aK)
                }
                if (F != null) {
                    var aN = F.pts;
                    if (D != null) {
                        aN.pop();
                        D = null
                    }
                    D = aK;
                    aN.push(D);
                    F.setPoints(aN)
                }
                break;
            case "rule":
                if (O == null) {
                    O = new al(7, aK, $cts, aC);
                    ae.addPanel(O.panel)
                } else {
                    O.updatePosition(aK)
                }
                if (x != null) {
                    var aN = x.pts;
                    if (L != null) {
                        aN.pop();
                        L = null
                    }
                    L = aK;
                    aN.push(L);
                    x.setPoints(aN);
                    var aG = e(x);
                    var aD = $ttd + "：<span style='color:red'>" + aG[0] + "</span>" + aG[1] + "<div style='color:#333333'>" + $ca + "</div>";
                    O.updateContent(aD)
                }
                break;
            case "coord":
                if (P) {
                    return
                }
                var aE = mapbarLatLonUtil.decrypt(aK.lon, aK.lat);
                var aD = "<span>" + aE[0] + ", " + aE[1] + "</span><div style='color:#808080'>" + $dcts + "</div>";
                if (l == null) {
                    l = new al(7, aK, aD, ay);
                    ae.addPanel(l.panel)
                } else {
                    l.updatePosition(aK);
                    l.updateContent(aD)
                }
                break;
            default:
                break
            }
        });
        E(Y, "dblclick",
        function(aI) {
            switch (n) {
            case "rule":
                u("pan");
                var aJ = aj(aI);
                var aK = new al(5, aJ, null, az);
                ae.addPanel(aK.panel);
                av.push(aK.panel);
                var aM = av;
                aK.div.onclick = function() {
                    am(aM)
                };
                ae.removePanel(O.panel, true);
                G();
                break;
            case "polygon":
                u("pan");
                ae.removePanel(ap.panel, true);
                if (ad && at) {
                    var aD = [];
                    var aN = at.pts;
                    for (var aG = 0; aG < aN.length; aG++) {
                        var aF = aN[aG];
                        var aE = mapbarLatLonUtil.decrypt(aF.lon, aF.lat);
                        aD.push({
                            lon: aE[0],
                            lat: aE[1]
                        })
                    }
                    var aL = new SEGPolygon();
                    aL.target = at;
                    aL.ps = aD;
                    aL.id = null;
                    aL.strokeColor = "red";
                    aL.strokeWeight = 2;
                    aL.strokeOpacity = 1;
                    aL.fillColor = "white";
                    aL.fillOpacity = 0.65;
                    A.push(aL);
                    ad(aD, aL)
                }
                N();
                break;
            case "polyline":
                u("pan");
                ae.removePanel(z.panel, true);
                if (ad && F) {
                    var aD = [];
                    var aN = F.pts;
                    for (var aG = 0; aG < aN.length; aG++) {
                        var aF = aN[aG];
                        var aE = mapbarLatLonUtil.decrypt(aF.lon, aF.lat);
                        aD.push({
                            lon: aE[0],
                            lat: aE[1]
                        })
                    }
                    var aH = new SEGPolyline();
                    aH.target = F;
                    aH.ps = aD;
                    aH.id = null;
                    aH.strokeColor = "red";
                    aH.strokeWeight = 2;
                    aH.strokeOpacity = 1;
                    A.push(aH);
                    ad(aD, aH)
                }
                o();
                break;
            case "coord":
                u("pan");
                if (l != null) {
                    ae.removePanel(l.panel, true);
                    l = null
                }
                break;
            default:
                break
            }
        });
        E(Y, "mousedown",
        function(aE) {
            P = true;
            var aD = aj(aE);
            switch (n) {
            case "circle":
                if (t == null) {
                    t = aD;
                    var aF = new MBrush();
                    aF.arrow = false;
                    aF.fill = true;
                    aF.color = "red";
                    aF.stroke = 2;
                    aF.transparency = 100;
                    aF.bgcolor = "white";
                    aF.bgtransparency = 65;
                    R = new MEllipse(t, 0, null, aF);
                    ae.addOverlay(R);
                    if (g != null) {
                        g.updateContent($mtcs)
                    }
                }
                break;
            case "rect":
                if (ag == null) {
                    ag = aD;
                    var aF = new MBrush();
                    aF.arrow = false;
                    aF.fill = true;
                    aF.color = "red";
                    aF.stroke = 2;
                    aF.transparency = 100;
                    aF.bgcolor = "white";
                    aF.bgtransparency = 65;
                    C = new MPolyline([aD, aD, aD, aD], aF);
                    ae.addOverlay(C);
                    if (h != null) {
                        h.updateContent($mtcs)
                    }
                }
                break;
            default:
                break
            }
        });
        E(Y, "mouseup",
        function(aN) {
            var aQ = aj(aN);
            switch (n) {
            case "circle":
                u("pan");
                ae.removePanel(g.panel, true);
                if (ad && R) {
                    var aK = y(t, aQ);
                    var aG = mapbarLatLonUtil.decrypt(t.lon, t.lat);
                    var aE = aG[0];
                    var aO = aG[1];
                    var aM = new SEGCircle();
                    aM.target = R;
                    aM.lon = aE;
                    aM.lat = aO;
                    aM.radius = aK;
                    aM.id = null;
                    aM.strokeColor = "red";
                    aM.strokeWeight = 2;
                    aM.strokeOpacity = 1;
                    aM.fillColor = "white";
                    aM.fillOpacity = 0.65;
                    A.push(aM);
                    ad(aE, aO, aK, aM)
                }
                s();
                break;
            case "rect":
                u("pan");
                ae.removePanel(h.panel, true);
                if (ad && C) {
                    var aI = SEGUtil.sortPoint(ag.lon, ag.lat, aQ.lon, aQ.lat);
                    var aJ = mapbarLatLonUtil.decrypt(aI[0], aI[1]);
                    var aD = mapbarLatLonUtil.decrypt(aI[2], aI[3]);
                    var aF = aJ[0];
                    var aR = aJ[1];
                    var aP = aD[0];
                    var aH = aD[1];
                    var aL = new SEGRectangle();
                    aL.target = C;
                    aL.lon1 = aF;
                    aL.lat1 = aR;
                    aL.lon2 = aP;
                    aL.lat2 = aH;
                    aL.id = null;
                    aL.strokeColor = "red";
                    aL.strokeWeight = 2;
                    aL.strokeOpacity = 1;
                    aL.fillColor = "white";
                    aL.fillOpacity = 0.65;
                    A.push(aL);
                    ad(aF, aR, aP, aH, aL)
                }
                W();
                break;
            default:
                break
            }
        });
        E(Y, "contextmenu",
        function(aD) {
            switch (n) {
            case "polygon":
                if (at != null) {
                    var aE = at.pts;
                    if (Z != null) {
                        aE.pop();
                        Z = null
                    }
                    aE.pop();
                    at.setPoints(aE)
                }
                break;
            case "polyline":
                if (F != null) {
                    var aE = F.pts;
                    if (D != null) {
                        aE.pop();
                        D = null
                    }
                    aE.pop();
                    F.setPoints(aE)
                }
                break;
            default:
                break
            }
        })
    }
    function aj(az) {
        var ax = az.x;
        var aD = az.y;
        var aA = ae.toMapCoordinate(ax, aD);
        var ay = aA.split(",");
        var aC = parseFloat(ay[0]);
        var aB = parseFloat(ay[1]);
        return new MPoint(aC, aB)
    }
    function al(aD, ax, aC, aA, az) {
        this.zIndex = aD;
        this.point = ax;
        this.content = aC;
        this.style = aA;
        this.className = az;
        this.div = document.createElement("div");
        this.div.style.position = "absolute";
        this.div.style.backgroundColor = "white";
        this.div.style.fontSize = "12px";
        if (this.content) {
            this.div.innerHTML = this.content
        }
        if (this.className) {
            this.div.className = this.className
        }
        if (this.style) {
            var aB = this.style;
            for (var ay in aB) {
                this.div.style[ay] = aB[ay]
            }
        }
        this.panel = new MPanel({
            pin: true,
            zindex: this.zIndex,
            content: this.div,
            mousewheel: true,
            location: {
                type: "latlon",
                x: 0,
                y: 0,
                pt: ax
            }
        })
    }
    al.prototype.hide = function() {
        this.div.style.display = "none"
    };
    al.prototype.show = function() {
        this.div.style.display = "block"
    };
    al.prototype.updateContent = function(ax) {
        this.content = ax;
        this.div.innerHTML = ax
    };
    al.prototype.updatePosition = function(ax) {
        this.point = ax;
        this.panel.setLocation({
            type: "latlon",
            x: 0,
            y: 0,
            pt: ax
        })
    };
    function m(az, aE, aF, aS) {
        if (typeof(az) == "object") {
            var aR = az;
            var aD = mapbarLatLonUtil.encrypt(parseFloat(aR.lon), parseFloat(aR.lat));
            var aM = new MPoint(aD[0], aD[1]);
            this.point = aM;
            var ax = 0;
            var aH = 0;
            var aA = 0;
            var ay = 0;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.div.style.zIndex = 0;
            var aN = 0;
            var aL = 0;
            if (aR.icon) {
                var aC = aR.icon.url;
                ax = aR.icon.width;
                aH = aR.icon.height;
                var aB = aR.icon.left || 0;
                var aK = aR.icon.top || 0;
                aA = (typeof(aR.icon.anchorx) == "undefined") ? -(ax / 2) : (aR.icon.anchorx);
                ay = (typeof(aR.icon.anchory) == "undefined") ? -aH: (aR.icon.anchory);
                aN = (typeof(aR.icon.winx) == "undefined") ? 0 : (aR.icon.winx);
                aL = (typeof(aR.icon.winy) == "undefined") ? -aH + 1 : (aR.icon.winy);
                this.iconDiv = new SEGUtil.Div(aA, ay, ax, aH).get();
                this.iconDiv.style.background = "url(" + aC + ") " + aB + "px " + aK + "px no-repeat";
                this.iconDiv.style.cursor = "pointer";
                if (aR.title) {
                    this.iconDiv.title = aR.title
                }
                this.div.appendChild(this.iconDiv)
            }
            if (aR.label) {
                var aI = (typeof(aR.label.anchorx) == "undefined") ? ax / 2 : aR.label.anchorx;
                var aG = (typeof(aR.label.anchory) == "undefined") ? -aH: aR.label.anchory;
                var aJ = aR.label.text;
                this.labelDiv = new SEGUtil.Div(aI, aG, null, null).get();
                this.labelDiv.innerHTML = aJ;
                var aQ = {
                    whiteSpace: "nowrap",
                    fontSize: "12px"
                };
                var aP = aR.label.style;
                if (aP) {
                    for (var aO in aP) {
                        aQ[aO] = aP[aO]
                    }
                }
                for (var aO in aQ) {
                    this.labelDiv.style[aO] = aQ[aO]
                }
                if (aR.title) {
                    this.labelDiv.title = aR.title
                }
                this.div.appendChild(this.labelDiv)
            }
            this.panel = new MPanel({
                pin: true,
                zindex: 4,
                content: this.div,
                mousewheel: true,
                location: {
                    type: "latlon",
                    x: 0,
                    y: 0,
                    pt: aM
                }
            });
            this.winx = aN;
            this.winy = aL
        } else {
            var aD = mapbarLatLonUtil.encrypt(parseFloat(az), parseFloat(aE));
            var aM = new MPoint(aD[0], aD[1]);
            this.point = aM;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.div.style.zIndex = 0;
            this.iconDiv = new SEGUtil.Div( - 6, -20, 12, 20).get();
            this.iconDiv.style.background = "url(" + SEGUtil.imageRootDir + "/marker.png) no-repeat";
            this.iconDiv.style.cursor = "pointer";
            if (aS) {
                this.iconDiv.title = aS
            }
            this.labelDiv = new SEGUtil.Div(8, -20, null, null).get();
            this.labelDiv.style.border = "1px solid red";
            this.labelDiv.innerHTML = aF;
            this.labelDiv.style.fontSize = "12px";
            this.labelDiv.style.padding = "1px";
            this.labelDiv.style.backgroundColor = "white";
            this.labelDiv.style.whiteSpace = "nowrap";
            if (isIE) {
                this.labelDiv.style.filter = "alpha(opacity=65)"
            } else {
                this.labelDiv.style.opacity = "0.65"
            }
            this.div.appendChild(this.iconDiv);
            this.div.appendChild(this.labelDiv);
            this.panel = new MPanel({
                pin: true,
                zindex: 4,
                content: this.div,
                mousewheel: true,
                location: {
                    type: "latlon",
                    x: 0,
                    y: 0,
                    pt: aM
                }
            });
            this.winx = 0;
            this.winy = -20
        }
    }
    m.prototype.getZIndex = function() {
        return this.panel.options.zindex
    };
    m.prototype.setZIndex = function(ax) {
        this.panel.setZIndex(ax)
    };
    function H(aO, aJ, aG, aC) {
        var aN = this;
        this.width = aG;
        this.height = aC;
        var aK = null;
        var aF = null;
        var az = 35;
        var aL = 15;
        if (typeof(aO) == "object") {
            aK = aO
        } else {
            aK = new SEGUtil.Div(15, aL, aG - 30, az).get();
            aK.style.borderBottom = "1px solid #ccc";
            aK.style.fontWeight = "bolder";
            aK.innerHTML = aO
        }
        this.titleDiv = aK;
        var aB = new SEGUtil.Div(0, 0, null, null).get();
        aB.style.cursor = "default";
        if (typeof(aJ) == "object") {
            aF = aJ;
            aF.style.left = "20px";
            aF.style.top = (az + aL) + "px"
        } else {
            aF = new SEGUtil.Div(20, az + aL, aG - 40, aC - az - aL).get()
        }
        var aM = document.createElement("div");
        aM.style.position = "absolute";
        aM.style.width = "10px";
        aM.style.height = "10px";
        aM.style.top = "6px";
        aM.style.right = "6px";
        aM.style.cursor = "pointer";
        aM.style.background = "url(" + SEGUtil.imageRootDir + "/iw_close.gif) no-repeat";
        aM.onclick = function() {
            ae.removePanel(aN.panel, false);
            aN.isShow = false
        };
        var ay = parseInt((aG - 22) / 2);
        var aD = document.createElement("div");
        aD.style.position = "absolute";
        aD.style.width = "51px";
        aD.style.height = "31px";
        aD.style.left = ay + "px";
        aD.style.bottom = "-31px";
        aD.style.background = "url(" + SEGUtil.imageRootDir + "/arrow_left.png) no-repeat";
        var ax = new SEGUtil.Div(0, 0, null, null).get();
        this.divPoint = ax;
        var aA = -(ay + 1);
        var aE = -(aC + 30);
        this.divWindowLeft = aA;
        this.divWindowTop = aE;
        var aI = new SEGUtil.Div(aA, aE, aG, aC).get();
        aI.style.background = "white";
        aI.style.border = "1px solid #999999";
        aI.appendChild(aK);
        aI.appendChild(aF);
        aI.appendChild(aM);
        aI.appendChild(aD);
        ax.appendChild(aI);
        aB.appendChild(ax);
        this.div = aB;
        var aH = new MPoint(114.110901, 22.553102);
        this.panel = new MPanel({
            pin: true,
            zindex: 9,
            content: this.div,
            mousewheel: true,
            location: {
                type: "latlon",
                x: 0,
                y: 0,
                pt: aH
            }
        })
    }
    var I = null;
    function i() {
        var ax = new MPoint(114.110901, 22.553102);
        var ay = "webmap_vehicle_hover_info_mapbar";
        I = new al(6, ax, "", null, ay);
        I.hide();
        ae.addPanel(I.panel)
    }
    function p(ax) {
        var ay = SEGUtil.getVehicleMarkerHoverInfo(at.opts,function(html){
        	h.innerHTML = html;
        });
        I.updateContent(ay);
        I.updatePosition(ax.point);
        I.show()
    }
    function X() {
        I.hide()
    }
    var K = "white";
    function Q(az) {
        var ay = this;
        this.opts = {};
        for (var ax in az) {
            this.opts[ax] = az[ax]
        }
        this.init = function() {
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            var aB = 25;
            this.iconDiv = new SEGUtil.Div( - 13, -13, 25, aB).get();
            this.numberPlateDiv = new SEGUtil.Div(13, -13, null, aB).get();
            this.div.appendChild(this.iconDiv);
            this.div.appendChild(this.numberPlateDiv);
            this.iconDiv.style.cursor = "pointer";
            this.iconDiv.onmouseover = function(aD) {
                p(ay)
            };
            this.iconDiv.onmouseout = function() {
                X()
            };
            this.updateIcon();
            this.numberPlateDiv.style.padding = "0 5px";
            this.numberPlateDiv.style.whiteSpace = "nowrap";
            this.numberPlateDiv.style.lineHeight = aB + "px";
            this.numberPlateDiv.style.border = "1px solid gray";
            this.numberPlateDiv.style.borderRadius = "2px";
            this.numberPlateDiv.style.background = K;
            if (isIE) {
                this.numberPlateDiv.filter = "alpha(opacity=70)"
            } else {
                this.numberPlateDiv.style.opacity = "0.7"
            }
            this.updateNumberPlate();
            var aC = mapbarLatLonUtil.encrypt(parseFloat(this.opts.lon), parseFloat(this.opts.lat));
            var aA = new MPoint(aC[0], aC[1]);
            this.point = aA;
            this.panel = new MPanel({
                pin: true,
                zindex: 5,
                content: this.div,
                mousewheel: true,
                location: {
                    type: "latlon",
                    x: 0,
                    y: 0,
                    pt: aA
                }
            })
        };
        this.updateNumberPlate = function() {
            this.numberPlateDiv.innerHTML = SEGUtil.parseNull(this.opts.label)
        };
        this.updateIcon = function() {
            var aB = this.opts;
            var aA = SEGUtil.getVehicleBackground(aB.course, aB.speed, aB.gpsTime, aB.isAlarm, aB.status, aB.isHistory);
            this.iconDiv.style.background = aA
        };
        this.updatePosition = function() {
            var aB = mapbarLatLonUtil.encrypt(parseFloat(this.opts.lon), parseFloat(this.opts.lat));
            var aA = new MPoint(aB[0], aB[1]);
            this.point = aA;
            this.panel.setLocation({
                type: "latlon",
                x: 0,
                y: 0,
                pt: aA
            })
        };
        this.update = function(aB) {
            for (var aA in aB) {
                this.opts[aA] = aB[aA]
            }
            this.updateNumberPlate();
            this.updateIcon();
            this.updatePosition()
        };
        this.flicker = function(aD, aG) {
            var aB = aD || 3000;
            var aE = aG || "red";
            var aC = K;
            var aF = this.numberPlateDiv;
            var aA = setInterval(function() {
                SEGUtil.flickerDiv(aF, aC, aE)
            },
            200);
            setTimeout(function() {
                clearInterval(aA);
                aF.style.backgroundColor = aC
            },
            aB)
        };
        this.init()
    }
    function y(ay, ax) {
        return ae.measDistance([ay, ax])
    }
    function e(ax) {
        var az = ax.pts;
        var ay = ae.measDistance(az);
        return SEGUtil.getDistanceDesc(ay)
    }
    var aa = null;
    var J = null;
    var ai = null;
    var v = null;
    this.setHistoryData = function(ay, ax) {
        aa = ay;
        J = ax
    };
    this.resetHistory = function() {
        r();
        J = null;
        aa = null
    };
    function r() {
        if (ai != null) {
            ae.removeOverlay(ai);
            ai = null
        }
        if (v != null) {
            ae.removePanel(v.panel, true);
            v = null
        }
    }
    this.playHistoryTo = function(aC) {
        if (ai == null) {
            var aG = [];
            for (var ay = 0; ay < J.length; ay++) {
                var aF = J[ay];
                var aB = mapbarLatLonUtil.encrypt(parseFloat(aF.lon), parseFloat(aF.lat));
                var aA = aB[0];
                var az = aB[1];
                var aE = new MPoint(aA, az);
                aG.push(aE)
            }
            var aD = new MBrush();
            aD.arrow = false;
            aD.fill = false;
            aD.color = "blue";
            aD.stroke = 2;
            aD.transparency = 100;
            ai = new MPolyline(aG, aD);
            ae.addOverlay(ai)
        }
        if (aC == -1) {
            if (v != null) {
                ae.removePanel(v.panel, true);
                v = null
            }
            _current_history_index = -1;
            return
        }
        if (aC < 0 || aC >= J.length) {
            return
        }
        var ax = J[aC];
        if (v == null) {
            for (var ay in aa) {
                ax[ay] = aa[ay]
            }
            v = new Q(ax);
            ae.addPanel(v.panel)
        } else {
            v.update(ax)
        }
        if (!f(ae, v.opts.lon, v.opts.lat)) {
            ae.setCenter(v.point)
        }
    };
    function f(ax, ay, aG) {
        var aC = ax.getViewBound();
        var aD = aC.LeftDown;
        var aB = aC.RightUp;
        var aF = aD.split(",");
        var aA = mapbarLatLonUtil.decrypt(parseFloat(aF[0]), parseFloat(aF[1]));
        var aE = aB.split(",");
        var az = mapbarLatLonUtil.decrypt(parseFloat(aE[0]), parseFloat(aE[1]));
        if (ay > aA[0] && ay < az[0] && aG > aA[1] && aG < az[1]) {
            return true
        }
        return false
    }
    this.getLocation = function(aC, aA, aE) {
        var aD = new Converter();
        var aB = aD.getEncryPoint(parseFloat(aC), parseFloat(aA));
        var az = BaiduConverter.encrypt(aB.x, aB.y);
        var ax = new BMap.Point(az.x, az.y);
        var ay = new BMap.Geocoder();
        ay.getLocation(ax,
        function(aF) {
            aE(aF.address)
        })
    }
};
var QQMapProxy = function(h, j) {
    var aa = this;
    var w = j;
    var af = [];
    var x = [];
    var q = [];
    var v = [af, x];
    var g = {
        mapTypeControl: true,
        scaleControl: true,
        scaleControlOptions: {
            position: qq.maps.ControlPosition.BOTTOM_RIGHT
        },
        center: new qq.maps.LatLng(22.553102, 114.110901),
        zoom: 14
    };
    var K = document.getElementById(h);
    var B = new qq.maps.Map(K, g);
    trafficControl = new b();
    B.controls[qq.maps.ControlPosition.TOP_RIGHT].push(trafficControl.getDiv());
    var A = new ad();
    B.controls[qq.maps.ControlPosition.RIGHT_TOP].push(A.getDiv());
    var L = null;
    var s = null;
    var o = null;
    var O = null;
    function y() {
        o = document.createElement("div");
        o.id = "pano_div";
        o.style.position = "absolute";
        o.style.left = "0";
        o.style.top = "0";
        o.style.zIndex = "-1";
        o.style.width = K.offsetWidth + "px";
        o.style.height = K.offsetHeight + "px";
        O = document.createElement("div");
        O.style.position = "absolute";
        O.style.display = "none";
        O.style.top = "10px";
        O.style.right = "35px";
        O.style.width = "18px";
        O.style.height = "18px";
        O.style.cursor = "pointer";
        O.style.background = "#878787";
        O.style.zIndex = "10";
        O.title = $closepn;
        O.onclick = function() {
            O.style.display = "none";
            o.style.zIndex = "-1";
            if (L) {
                L.setVisible(false)
            }
        };
        var ah = document.createElement("div");
        ah.style.position = "absolute";
        ah.style.top = "1px";
        ah.style.left = "1px";
        ah.style.width = "16px";
        ah.style.height = "16px";
        ah.style.background = "url(" + SEGUtil.imageRootDir + "/close_white_16.png) no-repeat";
        O.appendChild(ah);
        K.appendChild(o);
        K.appendChild(O)
    }
    this.init = function() {
        I();
        y()
    };
    var E = null;
    function c(ah) {
        var ai = {
            whiteSpace: "nowrap",
            padding: "5px 2px 2px 5px",
            margin: "-20px 0 0 -6px",
            cursor: "pointer",
            border: "1px solid #6482B9"
        };
        if (isIE) {
            ai.filter = "alpha(opacity=80)"
        } else {
            ai.opacity = "0.8"
        }
        E = new S(6, ah, "删除标注", null, ai);
        E.setMap(B);
        E.triggerMarker = null;
        W();
        qq.maps.event.addDomListener(E.div, "click",
        function() {
            w.deleteStaticMarker(E.triggerMarker);
            E.hide()
        })
    }
    var a = function() {
        if (E != null) {
            E.hide()
        }
    };
    function W() {
        if (document.addEventListener) {
            document.addEventListener("click", a, false)
        } else {
            if (document.attachEvent) {
                document.attachEvent("onclick", a)
            }
        }
    }
    function T() {
        if (document.addEventListener) {
            document.removeEventListener("click", a, false)
        } else {
            if (document.attachEvent) {
                document.detachEvent("onclick", a)
            }
        }
    }
    this.destroyMap = function() {
        T()
    };
    this.setCenter = function(ak, aj) {
        var al = new Converter();
        var ai = al.getEncryPoint(parseFloat(ak), parseFloat(aj));
        var ah = new qq.maps.LatLng(ai.y, ai.x);
        B.setCenter(ah)
    };
    this.centerAndZoom = function(ak, aj, am) {
        var al = new Converter();
        var ai = al.getEncryPoint(parseFloat(ak), parseFloat(aj));
        var ah = new qq.maps.LatLng(ai.y, ai.x);
        B.setCenter(ah);
        B.setZoom(am)
    };
    this.getCenter = function() {
        var ah = B.getCenter();
        var ak = ah.getLng();
        var aj = ah.getLat();
        var ai = Deconverter.decode(ak, aj);
        return {
            lon: ai.x,
            lat: ai.y
        }
    };
    this.isPointInView = function(ak, aj) {
        var al = new Converter();
        var ai = al.getEncryPoint(parseFloat(ak), parseFloat(aj));
        var ah = new qq.maps.LatLng(ai.y, ai.x);
        return B.getBounds().contains(ah)
    };
    this.getZoom = function() {
        return B.getZoom()
    };
    this.fitBounds = function(ao, al, an, ai) {
        var am = new Converter();
        var at = am.getEncryPoint(parseFloat(ao), parseFloat(al));
        var aj = new Converter();
        var ar = aj.getEncryPoint(parseFloat(an), parseFloat(ai));
        var ak = SEGUtil.sortPoint(at.x, at.y, ar.x, ar.y);
        var aq = new qq.maps.LatLng(ak[1], ak[0]);
        var ap = new qq.maps.LatLng(ak[3], ak[2]);
        var ah = new qq.maps.LatLngBounds(aq, ap);
        B.fitBounds(ah)
    };
    this.openDistanceTool = function() {
        F("rule")
    };
    this.openCoordTool = function() {
        F("coord")
    };
    this.drawPoint = function(ah) {
        N = ah;
        F("point")
    };
    this.drawCircle = function(ah) {
        N = ah;
        F("circle")
    };
    this.drawRectangle = function(ah) {
        N = ah;
        F("rect")
    };
    this.drawPolygon = function(ah) {
        N = ah;
        F("polygon")
    };
    this.drawPolyline = function(ah) {
        N = ah;
        F("polyline")
    };
    this.resize = function() {
        if (o) {
            var ai = K.offsetWidth;
            var ah = K.offsetHeight;
            o.style.width = ai + "px";
            o.style.height = ah + "px"
        }
    };
    this.newSimpleMarker = function(an, al, ak, am, ao) {
        var ai = new SEGSimpleMarker();
        var ah = new e(an, al, ak, am);
        ai.target = ah;
        if (typeof(an) == "object") {
            var aj = an;
            ai.config = aj;
            ai.lon = aj.lon;
            ai.lat = aj.lat;
            ai.label = aj.label;
            ai.title = aj.title;
            ai.id = aj.id
        } else {
            ai.lon = an;
            ai.lat = al;
            ai.label = ak;
            ai.title = am;
            ai.id = ao
        }
        return ai
    };
    this.setSimpleMarkerIcon = function(at, ai) {
        if (!this.isMarkerOnMap(at)) {
            return
        }
        var aj = ai.url;
        var ak = ai.width;
        var ar = ai.height;
        var am = ai.left || 0;
        var aq = ai.top || 0;
        var ap = (typeof(ai.anchorx) == "undefined") ? -(ak / 2) : (ai.anchorx);
        var ao = (typeof(ai.anchory) == "undefined") ? -ar: (ai.anchory);
        var an = (typeof(ai.winx) == "undefined") ? 0 : (ai.winx);
        var al = (typeof(ai.winy) == "undefined") ? -ar + 1 : (ai.winy);
        var ah = at.target.iconDiv;
        ah.style.width = ak + "px";
        ah.style.height = ar + "px";
        ah.style.background = "url(" + aj + ") " + am + "px " + aq + "px no-repeat";
        ah.style.left = ap + "px";
        ah.style.top = ao + "px";
        at.target.winx = an;
        at.target.winy = al
    };
    this.toTop = function(ah, ai) {
        ah.target.setZIndex(ai ? 1 : 0)
    };
    var J = [];
    this.newInfoWindow = function(aq, ap, aj, at, ai) {
        var ak = null;
        var al = null;
        var ar = 8;
        var an = 35;
        if (typeof(aq) == "object") {
            ak = aq
        } else {
            ak = new SEGUtil.Div(0, ar, null, an).get();
            ak.style.width = "100%";
            ak.style.padding = "0 5px 0 5px";
            ak.style.borderBottom = "1px solid #ccc";
            ak.style.fontWeight = "bolder";
            ak.innerHTML = aq
        }
        var ah = document.createElement("div");
        ah.style.overflow = "hidden";
        ah.style.width = aj + "px";
        ah.style.height = at + "px";
        ah.appendChild(ak);
        if (typeof(ap) == "object") {
            al = ap
        } else {
            al = new SEGUtil.Div(0, 0, aj, at - an - ar).get()
        }
        al.style.background = "#FAFAFA";
        al.style.top = (an + ar) + "px";
        ah.appendChild(al);
        var ao = new qq.maps.InfoWindow({
            map: B,
            content: ah
        });
        var am = new SEGInfoWindow();
        am.target = ao;
        am.titleDiv = ak;
        J.push(am);
        return am
    };
    this.showInfoWindow = function(ai, ah) {
        ah.target.setOptions({
            position: ai.target.point,
            pixelOffset: new qq.maps.Size(ai.target.winx, ai.target.winy)
        });
        ah.target.open()
    };
    this.setInfoWindowTitle = function(ah, ai) {
        ah.titleDiv.innerHTML = ai
    };
    this.isInfoWindowExist = function(ai) {
        var ah = SEGUtil.indexOfArray(J, ai);
        return ah != -1
    };
    this.closeInfoWindow = function(ah) {
        if (!this.isInfoWindowExist(ah)) {
            return
        }
        ah.target.close()
    };
    this.closeAllInfoWindow = function() {
        for (var ah = 0; ah < J.length; ah++) {
            J[ah].target.close()
        }
    };
    this.addEventListener = function(aj, ah, ai) {
        if (SEGSimpleMarker.prototype.isPrototypeOf(aj)) {
            qq.maps.event.addDomListener(aj.target.div, ah, ai)
        }
    };
    this.newVehicleMarker = function(ah) {};
    this.newCircle = function(ai, am, aj, aq, at, ay, ah, ak, av) {
        var az = new Converter();
        var ar = az.getEncryPoint(parseFloat(ai), parseFloat(am));
        var au = new qq.maps.LatLng(ar.y, ar.x);
        var an = typeof(av) == "undefined" ? 0.65 : av;
        var ax = typeof(ah) == "undefined" ? 1 : ah;
        var ap = typeof(ay) == "undefined" ? 2 : ay;
        var ao = {
            center: au,
            radius: aj,
            clickable: false,
            visible: true,
            fillColor: qq.maps.Color.fromHex(ak || "#FFFFFF", an),
            strokeColor: qq.maps.Color.fromHex(at || "#0000FF", ax),
            strokeWeight: ap
        };
        var al = new qq.maps.Circle(ao);
        var aw = new SEGCircle();
        aw.target = al;
        aw.lon = ai;
        aw.lat = am;
        aw.radius = aj;
        aw.id = aq;
        aw.strokeColor = at;
        aw.strokeWeight = ay;
        aw.strokeOpacity = ah;
        aw.fillColor = ak;
        aw.fillOpacity = av;
        return aw
    };
    this.newRectangle = function(aC, al, aB, aj, at, aw, az, ak, am, ax) {
        var av = new Converter();
        var ai = av.getEncryPoint(parseFloat(aC), parseFloat(al));
        var au = new Converter();
        var ah = au.getEncryPoint(parseFloat(aB), parseFloat(aj));
        var ar = d(ai.x, ai.y, ah.x, ah.y);
        var an = typeof(ax) == "undefined" ? 0.65 : ax;
        var ay = typeof(ak) == "undefined" ? 1 : ak;
        var aq = typeof(az) == "undefined" ? 2 : az;
        var ap = {
            path: ar,
            clickable: false,
            visible: true,
            fillColor: qq.maps.Color.fromHex(am || "#FFFFFF", an),
            strokeColor: qq.maps.Color.fromHex(aw || "#0000FF", ay),
            strokeWeight: aq
        };
        var ao = new qq.maps.Polygon(ap);
        var aA = new SEGRectangle();
        aA.target = ao;
        aA.lon1 = aC;
        aA.lat1 = al;
        aA.lon2 = aB;
        aA.lat2 = aj;
        aA.id = at;
        aA.strokeColor = aw;
        aA.strokeWeight = az;
        aA.strokeOpacity = ak;
        aA.fillColor = am;
        aA.fillOpacity = ax;
        return aA
    };
    this.newPolygon = function(at, ao, aq, ay, ai, aj, av) {
        var ar = [];
        for (var aw = 0; aw < at.length; aw++) {
            var az = at[aw];
            var aA = new Converter();
            var ap = aA.getEncryPoint(parseFloat(az.lon), parseFloat(az.lat));
            var au = new qq.maps.LatLng(ap.y, ap.x);
            ar.push(au)
        }
        var ak = typeof(av) == "undefined" ? 0.65 : av;
        var ax = typeof(ai) == "undefined" ? 1 : ai;
        var an = typeof(ay) == "undefined" ? 2 : ay;
        var am = {
            path: ar,
            clickable: false,
            visible: true,
            fillColor: qq.maps.Color.fromHex(aj || "#FFFFFF", ak),
            strokeColor: qq.maps.Color.fromHex(aq || "#0000FF", ax),
            strokeWeight: an
        };
        var al = new qq.maps.Polygon(am);
        var ah = new SEGPolygon();
        ah.target = al;
        ah.ps = at;
        ah.id = ao;
        ah.strokeColor = aq;
        ah.strokeWeight = ay;
        ah.strokeOpacity = ai;
        ah.fillColor = aj;
        ah.fillOpacity = av;
        return ah
    };
    this.newPolyline = function(ai, ak, ax, an, ap) {
        var aw = [];
        for (var am = 0; am < ai.length; am++) {
            var at = ai[am];
            var aq = new Converter();
            var aj = aq.getEncryPoint(parseFloat(at.lon), parseFloat(at.lat));
            var av = new qq.maps.LatLng(aj.y, aj.x);
            aw.push(av)
        }
        var al = typeof(ap) == "undefined" ? 1 : ap;
        var au = typeof(an) == "undefined" ? 2 : an;
        var ah = {
            path: aw,
            clickable: false,
            visible: true,
            strokeColor: qq.maps.Color.fromHex(ax || "#0000FF", al),
            strokeWeight: au
        };
        var ar = new qq.maps.Polyline(ah);
        var ao = new SEGPolyline();
        ao.target = ar;
        ao.ps = ai;
        ao.id = ak;
        ao.strokeColor = ax;
        ao.strokeWeight = an;
        ao.strokeOpacity = ap;
        return ao
    };
    this.addMarker = function(ah, ai) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(ah)) {
            ah.target.setMap(B);
            q.push(ah);
            return
        }
        var aj = null;
        if (typeof(ai) == "undefined" || ai == 0) {
            aj = af
        } else {
            if (ai == 1) {
                aj = x
            }
        }
        if (aj) {
            ah.target.setMap(B);
            aj.push(ah)
        }
        if (ai == 1 && SEGSimpleMarker.prototype.isPrototypeOf(ah)) {
            qq.maps.event.addDomListener(ah.target.iconDiv, "contextmenu",
            function(ak) {
                if (E == null) {
                    c(ah.target.point)
                } else {
                    E.updatePosition(ah.target.point);
                    E.show()
                }
                E.triggerMarker = ah
            })
        }
    };
    this.copyMarker = function(ah) {
        switch (ah.markerType) {
        case 1:
            if (ah.config) {
                return this.newSimpleMarker(ah.config)
            }
            return this.newSimpleMarker(ah.lon, ah.lat, ah.label, ah.title, ah.id);
        case 2:
            return this.newVehicleMarker(ah.opts);
        case 3:
            return this.newCircle(ah.lon, ah.lat, ah.radius, ah.id, ah.strokeColor, ah.strokeWeight, ah.strokeOpacity, ah.fillColor, ah.fillOpacity);
        case 4:
            return this.newRectangle(ah.lon1, ah.lat1, ah.lon2, ah.lat2, ah.id, ah.strokeColor, ah.strokeWeight, ah.strokeOpacity, ah.fillColor, ah.fillOpacity);
        case 5:
            return this.newPolygon(ah.ps, ah.id, ah.strokeColor, ah.strokeWeight, ah.strokeOpacity, ah.fillColor, ah.fillOpacity);
        case 6:
            return this.newPolyline(ah.ps, ah.id, ah.strokeColor, ah.strokeWeight, ah.strokeOpacity);
        default:
            return null
        }
    };
    this.isMarkerOnMap = function(ah) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(ah)) {
            for (var aj = 0; aj < q.length; aj++) {
                if (q[aj] == ah) {
                    return true
                }
            }
        } else {
            for (var aj = 0; aj < v.length; aj++) {
                var ak = v[aj];
                for (var ai = 0; ai < ak.length; ai++) {
                    if (ak[ai] == ah) {
                        return true
                    }
                }
            }
        }
        return false
    };
    this.addOrUpdateVehicleMarkerById = function(ah) {};
    this.removeMarker = function(ah) {
        if (SEGVehicleMarker.prototype.isPrototypeOf(ah)) {
            for (var aj = 0; aj < q.length; aj++) {
                if (q[aj] == ah) {
                    ah.target.setMap(null);
                    q.splice(aj, 1);
                    return true
                }
            }
        } else {
            for (var aj = 0; aj < v.length; aj++) {
                var ak = v[aj];
                for (var ai = 0; ai < ak.length; ai++) {
                    if (ak[ai] == ah) {
                        ah.target.setMap(null);
                        ak.splice(ai, 1);
                        return true
                    }
                }
            }
        }
        return false
    };
    this.clearNonStaticMarkers = function() {
        for (var ah = 0; ah < af.length; ah++) {
            af[ah].target.setMap(null)
        }
        af.splice(0, af.length)
    };
    this.clearStaticMarkers = function() {
        for (var ah = 0; ah < x.length; ah++) {
            x[ah].target.setMap(null)
        }
        x.splice(0, x.length)
    };
    this.clearVehicleMarkers = function() {
        for (var ah = 0; ah < q.length; ah++) {
            q[ah].target.setMap(null)
        }
        q.splice(0, q.length)
    };
    this.getNonStaticMarkers = function() {
        return af
    };
    this.getStaticMarkers = function() {
        return x
    };
    this.getVehicleMarkers = function() {
        return q
    };
    this.setHistoryData = function(ai, ah) {};
    this.resetHistory = function() {};
    this.playHistoryTo = function(ah) {};
    this.getLocation = function(al, ak, an) {
        var am = new Converter();
        var aj = am.getEncryPoint(parseFloat(al), parseFloat(ak));
        var ah = new qq.maps.LatLng(aj.y, aj.x);
        var ai = new qq.maps.Geocoder({
            complete: function(ao) {
                var ap = ao.detail.address;
                an(ap)
            }
        });
        ai.getAddress(ah)
    };
    var P = null;
    var N = null;
    var ae = null;
    var i = null;
    var M = null;
    var Q = null;
    function ac() {
        ae = null;
        i = null;
        M = null;
        Q = null
    }
    function C(ah) {
        for (var ai = 0; ai < ah.length; ai++) {
            ah[ai].setMap(null)
        }
    }
    var z = null;
    var G = null;
    var u = null;
    function V() {
        z = null;
        u = null;
        G = null
    }
    var Z = null;
    var ab = null;
    var H = null;
    function t() {
        Z = null;
        ab = null;
        H = null
    }
    var m = null;
    var ag = null;
    var f = null;
    function U() {
        m = null;
        ag = null;
        f = null
    }
    var r = null;
    var l = null;
    var p = null;
    function n() {
        r = null;
        l = null;
        p = null
    }
    var D = null;
    var k = null;
    var F = function(ai, ah) {
        P = ai;
        switch (P) {
        case "pan":
            B.setOptions({
                draggable:
                true,
                draggableCursor: null,
                draggingCursor: null
            });
            setTimeout(function() {
                B.setOptions({
                    disableDoubleClickZoom: false
                })
            },
            200);
            break;
        case "point":
            B.setOptions({
                draggableCursor:
                "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "circle":
            B.setOptions({
                draggable:
                false,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "rect":
            B.setOptions({
                draggable:
                false,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "polygon":
            B.setOptions({
                disableDoubleClickZoom:
                true,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "polyline":
            B.setOptions({
                disableDoubleClickZoom:
                true,
                draggableCursor: "crosshair",
                draggingCursor: "crosshair"
            });
            break;
        case "rule":
            B.setOptions({
                draggableCursor:
                "url('" + SEGUtil.imageRootDir + "/ruler.cur'), auto",
                draggingCursor: "url('" + SEGUtil.imageRootDir + "/ruler.cur'), auto",
                disableDoubleClickZoom: true
            });
            break;
        case "coord":
            B.setOptions({
                draggableCursor:
                "crosshair",
                draggingCursor: "crosshair",
                disableDoubleClickZoom: true
            });
            break;
        default:
            B.setOptions({
                draggableCursor:
                null,
                draggingCursor: null
            });
            break
        }
    };
    function I() {
        var ak = {
            zIndex: "1",
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -6px",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.png) no-repeat -25px -312px"
        };
        var aj = {
            zIndex: "1",
            width: "12px",
            height: "12px",
            margin: "-6px 0 0 -20px",
            cursor: "pointer",
            background: "url(" + SEGUtil.imageRootDir + "/mapctrls.gif) no-repeat 0px -14px"
        };
        var al = {
            zIndex: "1",
            whiteSpace: "nowrap",
            color: "#333333",
            height: "20px",
            border: "1px solid #333333",
            margin: "-6px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var am = {
            zIndex: "4",
            width: "120px",
            border: "1px solid red",
            margin: "20px 0 0 10px",
            padding: "0 2px 2px 2px"
        };
        var ai = {
            zIndex: "4",
            width: "140px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        var ah = {
            zIndex: "4",
            whiteSpace: "nowrap",
            width: "160px",
            border: "1px solid black",
            margin: "2px 0 0 2px",
            padding: "0 2px 2px 2px"
        };
        if (isIE) {
            al.filter = "alpha(opacity=70)";
            am.filter = "alpha(opacity=70)"
        } else {
            al.opacity = "0.7";
            am.opacity = "0.7"
        }
        qq.maps.event.addListener(B, "click",
        function(au) {
            if (A && A.isPanoramaLayerShowed()) {
                if (s == null) {
                    s = new qq.maps.PanoramaService()
                }
                var ax = au.latLng;
                var at;
                s.getPano(ax, at,
                function(aC) {
                    if (aC && aC.svid) {
                        if (A) {
                            A.hidePanoramaLayer()
                        }
                        if (L == null) {
                            L = new qq.maps.Panorama(o, {
                                pano: "10051001111220105028000",
                                disableMove: false,
                                disableFullScreen: false,
                                pov: {
                                    heading: 20,
                                    pitch: 15
                                },
                                zoom: 1
                            })
                        }
                        L.setPano(aC.svid);
                        L.setVisible(true);
                        o.style.zIndex = "1";
                        O.style.display = "block"
                    }
                })
            }
            var ax = au.latLng;
            switch (P) {
            case "point":
                F("pan");
                if (k != null) {
                    k.setMap(null);
                    k = null
                }
                if (N) {
                    var az = Deconverter.decode(ax.getLng(), ax.getLat());
                    var ao = Math.round(az.x * 1000000) / 1000000;
                    var aw = Math.round(az.y * 1000000) / 1000000;
                    N(ao, aw)
                }
                break;
            case "polygon":
                if (ag == null) {
                    var aB = [ax];
                    var an = {
                        path: aB,
                        clickable: false,
                        visible: true,
                        strokeColor: "#FF0000",
                        strokeWeight: 2,
                        strokeOpacity: 1,
                        fillColor: new qq.maps.Color(255, 255, 255, 0.65)
                    };
                    ag = new qq.maps.Polygon(an);
                    ag.setMap(B)
                } else {
                    var aA = ag.getPath();
                    if (f != null) {
                        aA.pop();
                        f = null
                    }
                    aA.push(ax);
                    ag.setPath(aA)
                }
                break;
            case "polyline":
                if (l == null) {
                    var aA = [ax];
                    var an = {
                        path: aA,
                        clickable: false,
                        visible: true,
                        strokeColor: "#FF0000",
                        strokeWeight: 2,
                        strokeOpacity: 1
                    };
                    l = new qq.maps.Polyline(an);
                    l.setMap(B)
                } else {
                    var aA = l.getPath();
                    if (p != null) {
                        aA.pop();
                        p = null
                    }
                    aA.push(ax);
                    l.setPath(aA)
                }
                break;
            case "rule":
                if (ae == null) {
                    i = [];
                    var aq = {
                        clickable: false,
                        path: [ax],
                        strokeColor: new qq.maps.Color(255, 0, 0, 0.6),
                        strokeWeight: 2,
                        map: B
                    };
                    ae = new qq.maps.Polyline(aq);
                    var ay = new S(1, ax, $sp, null, al);
                    ay.setMap(B);
                    i.push(ae);
                    i.push(ay)
                } else {
                    var av = ae.getPath();
                    if (Q != null) {
                        av.pop();
                        Q = null
                    }
                    av.push(ax);
                    ae.setPath(av);
                    var ar = Y(ae);
                    var ay = new S(1, ax, ar[0] + ar[1], null, al);
                    ay.setMap(B);
                    i.push(ay)
                }
                var ap = new S(1, ax, null, null, ak);
                ap.setMap(B);
                i.push(ap);
                break;
            default:
                break
            }
        });
        qq.maps.event.addListener(B, "rightclick",
        function(ao) {
            switch (P) {
            case "polygon":
                if (ag != null) {
                    var an = ag.getPath();
                    if (f != null) {
                        an.pop();
                        f = null
                    }
                    an.pop();
                    ag.setPath(an)
                }
                break;
            case "polyline":
                if (l != null) {
                    var an = l.getPath();
                    if (p != null) {
                        an.pop();
                        p = null
                    }
                    an.pop();
                    l.setPath(an)
                }
                break;
            default:
                break
            }
        });
        qq.maps.event.addListener(B, "mousemove",
        function(ar) {
            var av = ar.latLng;
            switch (P) {
            case "point":
                var aw = Deconverter.decode(av.getLng(), av.getLat());
                var ao = Math.round(aw.x * 1000000) / 1000000;
                var au = Math.round(aw.y * 1000000) / 1000000;
                var ap = "<span>" + ao + ", " + au + "</span><div style='color:#808080'>" + $ctsp + "</div>";
                if (k == null) {
                    k = new S(1, av, ap, null, ai);
                    k.setMap(B)
                } else {
                    k.updatePosition(av);
                    k.updateContent(ap)
                }
                break;
            case "circle":
                if (G == null) {
                    G = new S(1, av, $mdtscc, null, ai);
                    G.setMap(B)
                } else {
                    G.updatePosition(av)
                }
                if (u != null) {
                    G.updateContent($ctc);
                    var ax = R(z, av);
                    u.setRadius(ax)
                }
                break;
            case "rect":
                if (ab == null) {
                    ab = new S(1, av, $mdtsrs, null, ai);
                    ab.setMap(B)
                } else {
                    ab.updatePosition(av)
                }
                if (H != null) {
                    ab.updateContent($ctc);
                    var ay = d(Z.getLng(), Z.getLat(), av.getLng(), av.getLat());
                    H.setPath(ay)
                }
                break;
            case "polygon":
                if (m == null) {
                    var ap = "<span>" + $ctspgp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    m = new S(1, av, ap, null, ah);
                    m.setMap(B)
                } else {
                    m.updatePosition(av)
                }
                if (ag != null) {
                    var ay = ag.getPath();
                    if (f != null) {
                        ay.pop();
                        f = null
                    }
                    f = av;
                    ay.push(f);
                    ag.setPath(ay)
                }
                break;
            case "polyline":
                if (r == null) {
                    var ap = "<span>" + $ctsplp + "</span><div>" + $rctcls + "</div><div>" + $dcts + "</div>";
                    r = new S(1, av, ap, null, ah);
                    r.setMap(B)
                } else {
                    r.updatePosition(av)
                }
                if (l != null) {
                    var ay = l.getPath();
                    if (p != null) {
                        ay.pop();
                        p = null
                    }
                    p = av;
                    ay.push(p);
                    l.setPath(ay)
                }
                break;
            case "rule":
                if (M == null) {
                    M = new S(1, av, $cts, null, am);
                    M.setMap(B)
                } else {
                    M.updatePosition(av)
                }
                if (ae != null) {
                    var ay = ae.getPath();
                    if (Q != null) {
                        ay.pop();
                        Q = null
                    }
                    Q = ar.latLng;
                    ay.push(Q);
                    var aq = Y(ae);
                    var ap = $ttd + "：<span style='color:red'>" + aq[0] + "</span>" + aq[1] + "<div style='color:#333333'>" + $ca + "</div>";
                    M.updateContent(ap)
                }
                break;
            case "coord":
                var an = av.getLng();
                var at = av.getLat();
                var aw = Deconverter.decode(an, at);
                var ao = Math.round(aw.x * 1000000) / 1000000;
                var au = Math.round(aw.y * 1000000) / 1000000;
                var ap = "<span>" + ao + ", " + au + "</span><div style='color:#808080'>" + $dcts + "</div>";
                if (D == null) {
                    D = new S(1, av, ap, null, ai);
                    D.setMap(B)
                } else {
                    D.updatePosition(av);
                    D.updateContent(ap)
                }
                break;
            default:
                break
            }
        });
        qq.maps.event.addDomListener(B, "dblclick",
        function(at) {
            switch (P) {
            case "rule":
                at.stop();
                F("pan");
                var av = at.latLng;
                var ax = new S(5, av, null, null, aj);
                ax.setMap(B);
                i.push(ax);
                var az = i;
                qq.maps.event.addDomListener(ax.div, "click",
                function(aB) {
                    C(az)
                });
                M.setMap(null);
                ac();
                break;
            case "polygon":
                F("pan");
                m.setMap(null);
                if (N && ag) {
                    var an = [];
                    var aA = ag.getPath();
                    for (var aq = 0; aq < aA.length; aq++) {
                        var ap = aA.getAt(aq);
                        var aw = Deconverter.decode(ap.getLng(), ap.getLat());
                        var ao = Math.round(aw.x * 1000000) / 1000000;
                        var au = Math.round(aw.y * 1000000) / 1000000;
                        an.push({
                            lon: ao,
                            lat: au
                        })
                    }
                    var ay = new SEGPolygon();
                    ay.target = ag;
                    ay.ps = an;
                    ay.id = null;
                    ay.strokeColor = "#FF0000";
                    ay.strokeWeight = 2;
                    ay.strokeOpacity = 1;
                    ay.fillColor = "#FFFFFF";
                    ay.fillOpacity = 0.65;
                    af.push(ay);
                    N(an, ay)
                }
                U();
                break;
            case "polyline":
                F("pan");
                r.setMap(null);
                if (N && l) {
                    var an = [];
                    var aA = l.getPath();
                    for (var aq = 0; aq < aA.length; aq++) {
                        var ap = aA.getAt(aq);
                        var aw = Deconverter.decode(ap.getLng(), ap.getLat());
                        var ao = Math.round(aw.x * 1000000) / 1000000;
                        var au = Math.round(aw.y * 1000000) / 1000000;
                        an.push({
                            lon: ao,
                            lat: au
                        })
                    }
                    var ar = new SEGPolyline();
                    ar.target = l;
                    ar.ps = an;
                    ar.id = null;
                    ar.strokeColor = "#FF0000";
                    ar.strokeWeight = 2;
                    ar.strokeOpacity = 1;
                    af.push(ar);
                    N(an, ar)
                }
                n();
                break;
            case "coord":
                at.stop();
                F("pan");
                if (D != null) {
                    D.setMap(null);
                    D = null
                }
                break;
            default:
                break
            }
        });
        qq.maps.event.addListener(B, "mouseup",
        function(av) {
            var aC = av.latLng;
            switch (P) {
            case "circle":
                av.stop();
                F("pan");
                G.setMap(null);
                if (N && u) {
                    var an = z.getLng();
                    var az = z.getLat();
                    var au = R(z, aC);
                    var aD = Deconverter.decode(an, az);
                    var ao = Math.round(aD.x * 1000000) / 1000000;
                    var aA = Math.round(aD.y * 1000000) / 1000000;
                    var ax = new SEGCircle();
                    ax.target = u;
                    ax.lon = ao;
                    ax.lat = aA;
                    ax.radius = au;
                    ax.id = null;
                    ax.strokeColor = "#FF0000";
                    ax.strokeWeight = 2;
                    ax.strokeOpacity = 1;
                    ax.fillColor = "#FFFFFF";
                    ax.fillOpacity = 0.65;
                    af.push(ax);
                    N(ao, aA, au, ax)
                }
                V();
                break;
            case "rect":
                av.stop();
                F("pan");
                ab.setMap(null);
                if (N && H) {
                    var at = SEGUtil.sortPoint(Z.getLng(), Z.getLat(), aC.getLng(), aC.getLat());
                    var ay = Deconverter.decode(at[0], at[1]);
                    var aq = Math.round(ay.x * 1000000) / 1000000;
                    var aE = Math.round(ay.y * 1000000) / 1000000;
                    var ap = Deconverter.decode(at[2], at[3]);
                    var aB = Math.round(ap.x * 1000000) / 1000000;
                    var ar = Math.round(ap.y * 1000000) / 1000000;
                    var aw = new SEGRectangle();
                    aw.target = H;
                    aw.lon1 = aq;
                    aw.lat1 = aE;
                    aw.lon2 = aB;
                    aw.lat2 = ar;
                    aw.id = null;
                    aw.strokeColor = "#FF0000";
                    aw.strokeWeight = 2;
                    aw.strokeOpacity = 1;
                    aw.fillColor = "#FFFFFF";
                    aw.fillOpacity = 0.65;
                    af.push(aw);
                    N(aq, aE, aB, ar, aw)
                }
                t();
                break;
            default:
                break
            }
        });
        qq.maps.event.addListener(B, "mousedown",
        function(ar) {
            var an = ar.latLng;
            switch (P) {
            case "circle":
                if (z == null) {
                    z = an;
                    var ao = {
                        center: an,
                        clickable: false,
                        editable: false,
                        fillColor: new qq.maps.Color(255, 255, 255, 0.65),
                        radius: 0,
                        strokeColor: "#FF0000",
                        strokeWeight: 2,
                        visible: true
                    };
                    u = new qq.maps.Circle(ao);
                    u.setMap(B);
                    if (G != null) {
                        G.updateContent($mtcs)
                    }
                }
                break;
            case "rect":
                if (Z == null) {
                    Z = an;
                    var aq = d(an.getLng(), an.getLat(), an.getLng(), an.getLat());
                    var ap = {
                        path: aq,
                        clickable: false,
                        editable: false,
                        visible: true,
                        fillColor: new qq.maps.Color(255, 255, 255, 0.65),
                        strokeColor: "#FF0000",
                        strokeOpacity: 1,
                        strokeWeight: 2
                    };
                    H = new qq.maps.Polygon(ap);
                    H.setMap(B);
                    if (ab != null) {
                        ab.updateContent($mtcs)
                    }
                }
                break;
            default:
                break
            }
        })
    }
    function d(al, ai, ak, ah) {
        var aj = SEGUtil.sortPoint(al, ai, ak, ah);
        var ap = new qq.maps.LatLng(aj[3], aj[0]);
        var ao = new qq.maps.LatLng(aj[1], aj[0]);
        var an = new qq.maps.LatLng(aj[1], aj[2]);
        var am = new qq.maps.LatLng(aj[3], aj[2]);
        return [ap, ao, an, am]
    }
    function R(am, ah) {
        var aj = am.getLng();
        var al = am.getLat();
        var ai = ah.getLng();
        var ak = ah.getLat();
        return SEGUtil.getDistance(aj, al, ai, ak)
    }
    function Y(ah) {
        var al = 0;
        var an = ah.getPath();
        for (var ai = 0; ai < an.length - 1; ai++) {
            var ak = an.getAt(ai).getLng();
            var ao = an.getAt(ai).getLat();
            var aj = an.getAt(ai + 1).getLng();
            var am = an.getAt(ai + 1).getLat();
            al += SEGUtil.getDistance(ak, ao, aj, am)
        }
        return SEGUtil.getDistanceDesc(al)
    }
    function S(al, ah, aj, ak, ai) {
        this.zIndex = al;
        this.point = ah;
        this.content = aj;
        this.title = ak;
        this.style = ai;
        this.div = new SEGUtil.Div(0, 0, null, null).get()
    }
    S.prototype = new qq.maps.Overlay();
    S.prototype.construct = function() {
        this.div.style.backgroundColor = "white";
        if (this.content) {
            this.div.innerHTML = this.content
        }
        if (this.title) {
            this.div.title = this.title
        }
        if (this.style) {
            var aj = this.style;
            for (var ah in aj) {
                this.div.style[ah] = aj[ah]
            }
        }
        var ai = this.getPanes();
        switch (this.zIndex) {
        case 0:
            ai.mapPane.appendChild(this.div);
            break;
        case 1:
            ai.overlayLayer.appendChild(this.div);
            break;
        case 2:
            ai.overlayShadow.appendChild(this.div);
            break;
        case 3:
            ai.overlayImage.appendChild(this.div);
            break;
        case 4:
            ai.floatShadow.appendChild(this.div);
            break;
        case 5:
            ai.overlayMouseTarget.appendChild(this.div);
            break;
        case 6:
            ai.floatPane.appendChild(this.div);
            break;
        default:
            ai.mapPane.appendChild(this.div);
            break
        }
    };
    S.prototype.destroy = function() {
        this.div.parentNode.removeChild(this.div);
        this.div = null
    };
    S.prototype.draw = function() {
        var ah = this.getProjection();
        if (!ah) {
            return
        }
        var ai = ah.fromLatLngToDivPixel(this.point);
        this.div.style.left = (ai.x) + "px";
        this.div.style.top = (ai.y) + "px"
    };
    S.prototype.hide = function() {
        this.div.style.display = "none"
    };
    S.prototype.show = function() {
        this.div.style.display = "block"
    };
    S.prototype.updateContent = function(ah) {
        this.content = ah;
        this.div.innerHTML = ah
    };
    S.prototype.updatePosition = function(ah) {
        this.point = ah;
        this.draw()
    };
    function b() {
        var ao = document.createElement("div");
        ao.style.marginRight = "5px";
        ao.style.marginTop = "5px";
        var aj = document.createElement("div");
        aj.style.backgroundColor = "white";
        aj.style.border = "1px solid #666";
        aj.style.borderRadius = "2px";
        aj.style.padding = "1px 6px";
        aj.style.width = "78px";
        aj.style.lineHeight = "20px";
        aj.style.cursor = "pointer";
        aj.style.textAlign = "center";
        ao.appendChild(aj);
        var ai = document.createElement("div");
        ai.style.fontFamily = "Arial,sans-serif";
        ai.style.fontSize = "13px";
        ai.style.paddingLeft = "4px";
        ai.style.paddingRight = "4px";
        ai.innerHTML = $tfinf;
        aj.appendChild(ai);
        var ah = false;
        var al = null;
        var an = null;
        var am = this;
        qq.maps.event.addDomListener(aj, "click",
        function() {
            if (al == null) {
                al = new qq.maps.TrafficLayer()
            }
            if (an == null) {
                an = new X().getDiv()
            }
            if (!ah) {
                ah = true;
                aj.style.background = "#269AEA";
                aj.style.color = "white";
                aj.style.fontWeight = "bolder";
                al.setMap(B);
                B.controls[qq.maps.ControlPosition.TOP_RIGHT].push(an)
            } else {
                am.hideTrafficInfo()
            }
        });
        function ak(ap, ar) {
            for (var aq = 0; aq < ap.getLength(); aq++) {
                if (ap.getAt(aq) == ar) {
                    ap.removeAt(aq);
                    return
                }
            }
        }
        this.index = 1;
        this.hideTrafficInfo = function() {
            ah = false;
            aj.style.background = "white";
            aj.style.color = "black";
            aj.style.fontWeight = "normal";
            al.setMap(null);
            ak(B.controls[qq.maps.ControlPosition.TOP_RIGHT], an)
        };
        this.getDiv = function() {
            return ao
        }
    }
    function X() {
        var an = document.createElement("div");
        an.style.marginTop = "32px";
        an.style.marginRight = "-78px";
        an.style.position = "absolute";
        an.style.border = "1px solid #000";
        an.style.borderRadius = "2px";
        an.style.background = "#FFFFFF";
        an.style.width = "120px";
        an.style.height = "40px";
        var aj = document.createElement("div");
        aj.style.position = "absolute";
        aj.style.right = "3px";
        aj.style.top = "3px";
        aj.style.cursor = "pointer";
        aj.style.width = "12px";
        aj.style.height = "12px";
        aj.style.background = "url(" + SEGUtil.imageRootDir + "/popup_close.gif) no-repeat";
        aj.onclick = function() {
            if (trafficControl != null) {
                trafficControl.hideTrafficInfo()
            }
        };
        var ak = document.createElement("div");
        ak.style.position = "absolute";
        ak.style.left = "10px";
        ak.style.top = "5px";
        ak.style.width = "30px";
        ak.style.fontSize = "13px";
        ak.innerHTML = $tfinfc;
        var ai = document.createElement("div");
        ai.style.position = "absolute";
        ai.style.left = "65px";
        ai.style.top = "5px";
        ai.style.width = "30px";
        ai.style.fontSize = "13px";
        ai.innerHTML = $tfinfu;
        var ah = document.createElement("div");
        ah.style.position = "absolute";
        ah.style.left = "15px";
        ah.style.top = "25px";
        ah.style.height = "8px";
        ah.style.width = "18px";
        ah.style.background = "#FF0000";
        ah.style.border = "1px solid gray";
        var al = document.createElement("div");
        al.style.position = "absolute";
        al.style.left = "40px";
        al.style.top = "25px";
        al.style.height = "8px";
        al.style.width = "18px";
        al.style.background = "#ffe13a";
        al.style.border = "1px solid gray";
        var am = document.createElement("div");
        am.style.position = "absolute";
        am.style.left = "65px";
        am.style.top = "25px";
        am.style.height = "8px";
        am.style.width = "18px";
        am.style.background = "#00FF00";
        am.style.border = "1px solid gray";
        an.appendChild(aj);
        an.appendChild(ak);
        an.appendChild(ai);
        an.appendChild(ah);
        an.appendChild(al);
        an.appendChild(am);
        this.getDiv = function() {
            return an
        }
    }
    function ad() {
        var am = document.createElement("div");
        am.style.marginRight = "5px";
        am.style.marginTop = "5px";
        var ak = document.createElement("div");
        ak.style.backgroundColor = "white";
        ak.style.border = "1px solid #666";
        ak.style.borderRadius = "2px";
        ak.style.padding = "1px 6px";
        ak.style.lineHeight = "20px";
        ak.style.cursor = "pointer";
        ak.style.textAlign = "center";
        am.appendChild(ak);
        var aj = document.createElement("div");
        aj.style.fontFamily = "Arial,sans-serif";
        aj.style.fontSize = "13px";
        aj.style.paddingLeft = "4px";
        aj.style.paddingRight = "4px";
        aj.innerHTML = $pninf;
        ak.appendChild(aj);
        var ai = false;
        this.isPanoramaLayerShowed = function() {
            return ai
        };
        var ah = null;
        var al = this;
        qq.maps.event.addDomListener(ak, "click",
        function() {
            if (ah == null) {
                ah = new qq.maps.PanoramaLayer()
            }
            if (!ai) {
                ah.setMap(B);
                ai = true;
                ak.style.background = "#269AEA";
                ak.style.color = "white"
            } else {
                al.hidePanoramaLayer()
            }
        });
        this.hidePanoramaLayer = function() {
            if (ah) {
                ah.setMap(null)
            }
            ai = false;
            ak.style.background = "white";
            ak.style.color = "black"
        };
        this.getDiv = function() {
            return am
        }
    }
    function e(aj, an, ao, aE) {
        var ay = 0;
        var aw = 0;
        if (typeof(aj) == "object") {
            var aD = aj;
            var aC = new Converter();
            var av = aC.getEncryPoint(parseFloat(aD.lon), parseFloat(aD.lat));
            var ax = new qq.maps.LatLng(av.y, av.x);
            this.point = ax;
            var ah = 0;
            var aq = 0;
            var ak = 0;
            var ai = 0;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.div.style.zIndex = 0;
            if (aD.icon) {
                var am = aD.icon.url;
                ah = aD.icon.width;
                aq = aD.icon.height;
                var al = aD.icon.left || 0;
                var au = aD.icon.top || 0;
                ak = (typeof(aD.icon.anchorx) == "undefined") ? -(ah / 2) : (aD.icon.anchorx);
                ai = (typeof(aD.icon.anchory) == "undefined") ? -aq: (aD.icon.anchory);
                ay = (typeof(aD.icon.winx) == "undefined") ? 0 : (aD.icon.winx);
                aw = (typeof(aD.icon.winy) == "undefined") ? -aq + 1 : (aD.icon.winy);
                this.iconDiv = new SEGUtil.Div(ak, ai, ah, aq).get();
                this.iconDiv.style.background = "url(" + am + ") " + al + "px " + au + "px no-repeat";
                this.iconDiv.style.cursor = "pointer";
                if (aD.title) {
                    this.iconDiv.title = aD.title
                }
                this.div.appendChild(this.iconDiv)
            }
            if (aD.label) {
                var ar = (typeof(aD.label.anchorx) == "undefined") ? ah / 2 : aD.label.anchorx;
                var ap = (typeof(aD.label.anchory) == "undefined") ? -aq: aD.label.anchory;
                var at = aD.label.text;
                this.labelDiv = new SEGUtil.Div(ar, ap, null, null).get();
                this.labelDiv.innerHTML = at;
                var aB = {
                    whiteSpace: "nowrap",
                    fontSize: "12px"
                };
                var aA = aD.label.style;
                if (aA) {
                    for (var az in aA) {
                        aB[az] = aA[az]
                    }
                }
                for (var az in aB) {
                    this.labelDiv.style[az] = aB[az]
                }
                if (aD.title) {
                    this.labelDiv.title = aD.title
                }
                this.div.appendChild(this.labelDiv)
            }
        } else {
            var aC = new Converter();
            var av = aC.getEncryPoint(parseFloat(aj), parseFloat(an));
            var ax = new qq.maps.LatLng(av.y, av.x);
            this.point = ax;
            this.label = ao;
            this.div = new SEGUtil.Div(0, 0, null, null).get();
            this.iconDiv = new SEGUtil.Div(0, 0, 12, 20).get();
            this.labelDiv = new SEGUtil.Div(0, 0, null, null).get();
            this.labelDiv.style.border = "1px solid red";
            this.labelDiv.innerHTML = ao;
            this.labelDiv.style.fontSize = "12px";
            this.labelDiv.style.padding = "1px";
            this.labelDiv.style.margin = "-20px 0 0 8px";
            this.labelDiv.style.backgroundColor = "white";
            this.labelDiv.style.whiteSpace = "nowrap";
            if (isIE) {
                this.labelDiv.style.filter = "alpha(opacity=65)"
            } else {
                this.labelDiv.style.opacity = "0.65"
            }
            this.iconDiv.style.margin = "-20px 0 0 -6px";
            this.iconDiv.style.background = "url(" + SEGUtil.imageRootDir + "/marker.png) no-repeat";
            this.iconDiv.style.cursor = "pointer";
            if (aE) {
                this.iconDiv.title = aE
            }
            this.div.appendChild(this.iconDiv);
            this.div.appendChild(this.labelDiv)
        }
        this.winx = ay;
        this.winy = aw
    }
    e.prototype = new qq.maps.Overlay();
    e.prototype.construct = function() {
        var ah = this.getPanes();
        ah.overlayMouseTarget.appendChild(this.div)
    };
    e.prototype.destroy = function() {
        this.div.parentNode.removeChild(this.div);
        this.div = null
    };
    e.prototype.draw = function() {
        var ah = this.getProjection();
        var ai = ah.fromLatLngToDivPixel(this.point);
        this.div.style.left = (ai.x) + "px";
        this.div.style.top = (ai.y) + "px"
    };
    e.prototype.getZIndex = function() {
        return this.div.style.zIndex
    };
    e.prototype.setZIndex = function(ah) {
        this.div.style.zIndex = ah
    }
};
var $ca = "单击测距，双击结束";
var $cb = "米";
var $cc = "千米";
var $dm = "删除标注";
var $cts = "单击确定起点";
var $sp = "起点";
var $ttd = "总长";
var $dcts = "双击结束";
var $ctsp = "单击选择点";
var $mdtscc = "按下鼠标选择中心点";
var $mdtsrs = "按下鼠标选择起点";
var $mtcs = "移动鼠标改变大小";
var $ctc = "放开鼠标确定选择";
var $pninf = "全景";
var $closepn = "关闭全景";
var $tfinf = "路况信息";
var $tfinfc = "拥堵";
var $tfinfu = "畅通";
var $ctspgp = "单击选择多边形的一个顶点";
var $ctsplp = "单击选择折线的一个顶点";
var $rctcls = "右击取消最后选择的顶点";
var $gpstime = "GPS时间";
var $stamp = "入库时间";
var $speed = "速度";
var $status = "状态";
var $speedq = "千米/小时";
var $day = "天";
var $hours = "小时";
var $minus = "分钟";
var $sec = "秒";
var $hide = "隐藏";
var $show = "显示";
var $close = "关闭";
var $play = "播放";
var $pause = "暂停";
var $stop = "停止";
var $slow = "慢放";
var $quick = "快放";
var $playend = "播放完毕";
var $search = "搜索";
var $searchnb = "周边搜索";
var $searchr = "周边搜索半径";
var $raderror = "半径错误";
var $hotel = "酒店";
var $restaurant = "餐馆";
var $bank = "银行";
var $atm = "ATM";
var $hospital = "医院";
var $carpark = "停车场";
var $stations = "加油站";
var $navstartp = "设为起点";
var $navendp = "设为终点";
var isIE = ("\v" == "v");
var SEGMap = function(h, b) {
    var i = h;
    var d = null;
    var c = this;
    var j = a(b);
    var f = function() {
        c.resize()
    };
    if (document.addEventListener) {
        window.addEventListener("resize", f, false)
    } else {
        if (document.attachEvent) {
            window.attachEvent("onresize", f)
        }
    }
    this.getOriMap = function(){return j.getOriMap();};
    this.getDiv = function() {
        return document.getElementById(i)
    };
    this.setCenter = function(l, k) {
        j.setCenter(l, k)
    };
    this.centerAndZoom = function(l, k, m) {
        j.centerAndZoom(l, k, m)
    };
    this.getCenter = function() {
        return j.getCenter()
    };
    this.isPointInView = function(l, k) {
        return j.isPointInView(l, k)
    };
    this.getZoom = function() {
        return j.getZoom()
    };
    this.fitBounds = function(l, n, k, m) {
        return j.fitBounds(l, n, k, m)
    };
    this.openDistanceTool = function() {
        j.openDistanceTool()
    };
    this.openCoordTool = function() {
        j.openCoordTool()
    };
    this.drawPoint = function(k) {
        j.drawPoint(k)
    };
    this.drawCircle = function(k) {
        j.drawCircle(k)
    };
    this.drawRectangle = function(k) {
        j.drawRectangle(k)
    };
    this.drawPolygon = function(k) {
        j.drawPolygon(k)
    };
    this.drawPolyline = function(k) {
        j.drawPolyline(k)
    };
    this.resize = function() {
        j.resize();
        if (e != null) {
            e.resize()
        }
    };
    this.newSimpleMarker = function(n, l, k, m, o) {
        return j.newSimpleMarker(n, l, k, m, o)
    };
    this.setSimpleMarkerIcon = function(k, l) {
        j.setSimpleMarkerIcon(k, l)
    };
    this.toTop = function(k, l) {
        return j.toTop(k, l)
    };
    this.newInfoWindow = function(n, m, l, k, o) {
        return j.newInfoWindow(n, m, l, k, o)
    };
    this.showInfoWindow = function(l, k) {
        j.showInfoWindow(l, k)
    };
    this.setInfoWindowTitle = function(k, l) {
        j.setInfoWindowTitle(k, l)
    };
    this.isInfoWindowExist = function(k) {
        return j.isInfoWindowExist(k)
    };
    this.closeInfoWindow = function(k) {
        j.closeInfoWindow(k)
    };
    this.closeAllInfoWindow = function() {
        j.closeAllInfoWindow()
    };
    this.addEventListener = function(m, k, l) {
        j.addEventListener(m, k, l)
    };
    this.newVehicleMarker = function(k) {
        return j.newVehicleMarker(k)
    };
    this.newCircle = function(k, q, o, l, s, n, p, m, r) {
        return j.newCircle(k, q, o, l, s, n, p, m, r)
    };
    this.newRectangle = function(q, n, p, m, l, t, o, r, k, s) {
        return j.newRectangle(q, n, p, m, l, t, o, r, k, s)
    };
    this.newPolygon = function(o, q, n, m, l, p, k) {
        return j.newPolygon(o, q, n, m, l, p, k)
    };
    this.newPolyline = function(n, o, m, l, k) {
        return j.newPolyline(n, o, m, l, k)
    };
    this.addMarker = function(l, k) {
        j.addMarker(l, k)
    };
    this.copyMarker = function(k) {
        return j.copyMarker(k)
    };
    this.isMarkerOnMap = function(k) {
        return j.isMarkerOnMap(k)
    };
    this.addOrUpdateVehicleMarkerById = function(k) {
        return j.addOrUpdateVehicleMarkerById(k)
    };
    this.removeMarker = function(k) {
        j.removeMarker(k)
    };
    this.clearNonStaticMarkers = function() {
        j.clearNonStaticMarkers()
    };
    this.clearStaticMarkers = function() {
        j.clearStaticMarkers()
    };
    this.clearVehicleMarkers = function() {
        j.clearVehicleMarkers()
    };
    this.getNonStaticMarkers = function() {
        return j.getNonStaticMarkers()
    };
    this.getStaticMarkers = function() {
        return j.getStaticMarkers()
    };
    this.getVehicleMarkers = function() {
        return j.getVehicleMarkers()
    };
    this.setHistoryData = function(l, k) {
        j.setHistoryData(l, k)
    };
    this.resetHistory = function() {
        j.resetHistory()
    };
    this.playHistoryTo = function(k) {
        j.playHistoryTo(k)
    };
    this.getLocation = function(l, k, m) {
        j.getLocation(l, k, m)
    };
    this.deleteStaticMarker = function(k) {
        j.removeMarker(k)
    };
    this.getMapType = function() {
        return d
    };
    this.switchMap = function(m) {
        if (d == m) {
            return
        }
        var k = this.getCenter();
        var l = this.getZoom();
        j.destroyMap();
        if (e != null) {
            e.closeHistory();
            e.clearEvents()
        }
        g(i);
        e = null;
        j = a(m);
        j.centerAndZoom(k.lon, k.lat, l)
    };
    var e = null;
    this.getHistoryControl = function() {
        return e
    };
    this._history_callback1 = null;
    this._history_callback2 = null;
    this._history_callback3 = null;
    this.playHistory = function(o, p, n, m, l) {
        this._history_callback1 = n;
        this._history_callback2 = m;
        this._history_callback3 = l;
        if (e == null) {
            e = new SEGPlayControl(this);
            var k = document.getElementById(h);
            k.appendChild(e.div);
        }
        e.initData(o, p);
        e.show();
        e.play();
    };
    function g(k) {
        var l = document.getElementById(k);
        while (l.children.length > 0) {
            l.removeChild(l.children[0])
        }
    }
    function a(k) {
        var l;
        if ("baidu" == k) {
            d = k;
            l = new BaiduMapProxy(h, c)
        } else {
            if ("mapbar" == k) {
                d = k;
                l = new MapbarMapProxy(h, c)
            } else {
                if ("google" == k) {
                    d = k;
                    l = new GoogleMapProxy(h, c)
                } else {
                    if ("qq" == k) {
                        d = k;
                        l = new QQMapProxy(h, c)
                    } else {
                        return null
                    }
                }
            }
        }
        l.init();
        return l
    }
};
var SEGSimpleMarker = function() {
    this.markerType = 1;
    this.target = null;
    this.lon = null;
    this.lat = null;
    this.label = null;
    this.title = null;
    this.id = null
};
var SEGInfoWindow = function() {
    this.target = null
};
var SEGVehicleMarker = function() {
    this.markerType = 2;
    this.target = null;
    this.opts = null
};
var SEGCircle = function() {
    this.markerType = 3;
    this.target = null;
    this.lon = null;
    this.lat = null;
    this.radius = null;
    this.id = null;
    this.strokeColor = null;
    this.strokeWeight = null;
    this.strokeOpacity = null;
    this.fillColor = null;
    this.fillOpacity = null
};
var SEGRectangle = function() {
    this.markerType = 4;
    this.target = null;
    this.lon1 = null;
    this.lat1 = null;
    this.lon2 = null;
    this.lat2 = null;
    this.id = null;
    this.strokeColor = null;
    this.strokeWeight = null;
    this.strokeOpacity = null;
    this.fillColor = null;
    this.fillOpacity = null
};
var SEGPolygon = function() {
    this.markerType = 5;
    this.target = null;
    this.ps = null;
    this.id = null;
    this.strokeColor = null;
    this.strokeWeight = null;
    this.strokeOpacity = null;
    this.fillColor = null;
    this.fillOpacity = null
};
var SEGPolyline = function() {
    this.markerType = 6;
    this.target = null;
    this.ps = null;
    this.id = null;
    this.strokeColor = null;
    this.strokeWeight = null;
    this.strokeOpacity = null
};
var SEGPoint = function(b, a) {
    this.lon = b;
    this.lat = a
};
var SEGUtil = {
    Div: function(d, c, b, a) {
        this.div = document.createElement("div");
        this.style = this.div.style;
        this.style.position = "absolute";
        this.style.fontSize = 12 + "px";
        if (d) {
            this.style.left = d + "px"
        } else {
            this.style.left = 0 + "px"
        }
        if (c) {
            this.style.top = c + "px"
        } else {
            this.style.top = 0 + "px"
        }
        if (b) {
            this.style.width = b + "px"
        }
        if (a) {
            this.style.height = a + "px"
        }
        this.style.display = "block";
        this.get = function() {
            return this.div
        };
        this.toString = function() {
            return "div"
        }
    },
    addEventForDom: function(b, a, c) {
        if (document.addEventListener) {
            b.addEventListener(a, c, false)
        } else {
            if (document.attachEvent) {
                b.attachEvent("on" + a, c)
            }
        }
    },
    removeEventForDom: function(b, a, c) {
        if (document.addEventListener) {
            b.removeEventListener(a, c, false)
        } else {
            if (document.attachEvent) {
                b.detachEvent("on" + a, c)
            }
        }
    },
    getCharCount: function(c) {
        var b = 0;
        for (var a = 0; a < c.length; a++) {
            if (c.charCodeAt(a) > 255) {
                b += 2
            } else {
                b += 1
            }
        }
        return b
    },
    sortPoint: function(e, h, c, g) {
        var b, f, a, d;
        if (e < c) {
            b = e;
            f = c
        } else {
            b = c;
            f = e
        }
        if (h < g) {
            a = h;
            d = g
        } else {
            a = g;
            d = h
        }
        return [b, a, f, d]
    },
    getOffsetXY: function(e, a) {
        var d = e.offsetLeft;
        var c = e.offsetTop;
        var b = e.offsetParent;
        while (b && b != a) {
            d += b.offsetLeft;
            c += b.offsetTop;
            b = b.offsetParent
        }
        return {
            x: d,
            y: c
        }
    },
    getEventOffsetXY: function(b, h) {
        var g = window.event || b;
        var d = g.clientX;
        var c = g.clientY;
        var f = SEGUtil.getOffsetXY(h, document.body);
        var a = d - f.x;
        var i = c - f.y;
        return {
            x: a,
            y: i
        }
    },
    getCourseIndex: function(a) {
        var b = a % 360 * 10;
        var d = parseInt(b / 225);
        var c = parseInt((d + 1) / 2);
        if (c >= 8) {
            c = 0
        }
        return c
    },
    courseDescs: ["向北", "北偏东", "向东", "南偏东", "向南", "南偏西", "向西", "北偏西"],
    getCourseDesc: function(b) {
        var a = this.getCourseIndex(b);
        return this.courseDescs[a]
    },
    getVehicleBackgroundX: function(b) {
        var a = this.getCourseIndex(b);
        return 25 * a
    },
    getVehicleBackgroundY: function(c, i, j, e, a) {
        var b = parseInt(j);
        if (b) {
            return 50
        }
        if (!a) {
            var g = i.indexOf(".");
            if (g > 0) {
                i = i.substring(0, g)
            }
            var d = Date.parse(i.replace(/-/g, "/"));
            var h = new Date().getTime();
            var f = (h - d) / 60000;
            if (f > 30 || f < -10000) {
                return 75
            }
        }
        if (e) {
            if (typeof(e) == "string") {
                e = e.split(",")
            }
            if (this.indexOfArray(e, 23) != -1) {
                return 100
            }
        }
        if (typeof c != "undefined") {
            if (c <= 10) {
                return 25
            }
            return 0
        }
        return 75
    },
    indexOfArray: function(a, b) {
        for (var c = 0; c < a.length; c++) {
            if (a[c] == b) {
                return c
            }
        }
        return - 1
    },
    getVehicleBackground: function(f, c, b, i, d, a) {
        var h = this.getVehicleBackgroundX(f);
        var g = this.getVehicleBackgroundY(c, b, i, d, a);
        var e = "url(" + SEGUtil.imageRootDir + "/cars.png) no-repeat -" + h + "px -" + g + "px";
        return e
    },
    parseNull: function(a) {
        return ((typeof(a) == "undefined" || a == null) ? "": a)
    },
    parseVehicleStatus: function(a) {
        if (!a) {
            return ""
        }
        if (typeof(a) == "string") {
            a = a.split(",")
        }
        var d = "";
        for (var b = 0; b < a.length; b++) {
            var c = SEGVehicleStatus[a[b]];
            if (typeof(c) != "undefined") {
                d += c + ","
            }
        }
        if (d.length > 0) {
            d = d.substring(0, d.length - 1)
        }
        return d
    },
    flickerDiv: function(d, c, b) {
        var a = d.style.backgroundColor.toLowerCase();
        if (a == c) {
            d.style.backgroundColor = b
        } else {
            d.style.backgroundColor = c
        }
    },
    getDistanceDesc: function(c) {
        var b;
        var a;
        if (c > 1000) {
            a = Math.round(c / 10) / 100;
            b = $cc
        } else {
            a = Math.round(c * 100) / 100;
            b = $cb
        }
        return [a, b]
    },
    getTimeDesc: function(f) {
        var a = 86400;
        var c = 3600;
        var d = 60;
        if (f > a) {
            var g = parseInt(f / f);
            var b = Math.ceil((f - g * a) / c);
            return [[g, $day], [b, $hours]]
        }
        if (f > c) {
            var b = parseInt(f / c);
            var e = Math.ceil((f - b * c) / d);
            return [[b, $hours], [e, $minus]]
        }
        if (f > d) {
            var e = Math.ceil(f / d);
            return [[e, $minus]]
        }
        return [[1, $minus]]
    },
    EARTH_RADIUS: 6378136.49,
    getRad: function(a) {
        return a * Math.PI / 180
    },
    getDistance: function(f, d, e, c) {
        var h = this.getRad(d);
        var g = this.getRad(c);
        var j = h - g;
        var i = this.getRad(f) - this.getRad(e);
        var k = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(j / 2), 2) + Math.cos(h) * Math.cos(g) * Math.pow(Math.sin(i / 2), 2)));
        k = k * this.EARTH_RADIUS;
        k = Math.round(k * 10000) / 10000;
        return k
    },
    getVehicleMarkerHoverInfo: function(e) {
        var d = SEGUtil.parseNull(e.numberPlate);
        var g = SEGUtil.parseNull(e.gpsTime);
        var c = SEGUtil.parseNull(e.stamp);
        var f = SEGUtil.parseNull(e.speed);
        /*var a = "";
    	//状态特殊处理，只显示点火、熄火状态
    	var statusArr = new Array();
    	for ( var status in e.status) {
    		if(e.status[status] ==23){
    			statusArr.push(23);
    			break;
    		}else if(e.status[status] ==33){
    			statusArr.push(33);
    			break;
    		}
    	}
    	a = SEGUtil.parseVehicleStatus(statusArr);*/
        var a = SEGUtil.parseVehicleStatus(e.status);
        var b = "<div style='width:100%;text-align:center'>" + d + "</div>";
        b += "<hr size=1 style='margin:2px 0 5px 0;width:100%'>";
        b += "<p>" + $gpstime + ": " + g + "</p>";
        b += "<p>" + $stamp + ": " + c + "</p>";
        b += "<p>" + $speed + ": " + f + $speedq + "</p>";
        b += "<p>" + $status + ": " + a + "</p>";
        return b
    },
    imageRootDir: "resources/images/map"
};
var SEGPlayControl = function(r) {
    var H = r;
    var af = this;
    var B = 26;
    var N = 26;
    var R = 5;
    var h = 5;
    var P = 18;
    var V = 16;
    var ao = 6;
    var O = 250 + ao;
    var ai = 15;
    var W = (B * 4 + 5 * h);
    var ad = (N + 2 * R - ai) / 2;
    var an = document.createElement("div");
    this.div = an;
    var Z = (N + 2 * R);
    var ah = B * 4 + 9 * h + O + V + P;
    var i = (H.getDiv().offsetWidth - ah) / 2;
    an.style.position = "absolute";
    an.style.zIndex = "10";
    an.style.bottom = 60+"px";
    an.style.left = i + "px";
    var K = 200;
    var S = 38;
    var n = 18;
    var ab = 18;
    var s = parseInt((K - n - 2) / 2);
    var aq = S - 4;
    var o = parseInt(s + n / 2) + 1;
    var v = -(S + ab - ad - 4);
    var d = document.createElement("div");
    d.style.position = "absolute";
    d.style.left = "0px";
    d.style.top = v + "px";
    d.style.display = "none";
    d.style.width = K + "px";
    d.style.height = S + "px";
    d.style.border = "1px solid gray";
    d.style.background = "#FFFFFF";
    var aj = document.createElement("div");
    aj.style.position = "absolute";
    aj.style.left = "5px";
    aj.style.top = "5px";
    aj.style.width = "25px";
    aj.style.height = "25px";
    aj.style.background = "url(" + SEGUtil.imageRootDir + "/cars.png) no-repeat";
    var l = document.createElement("div");
    l.style.position = "absolute";
    l.style.height = "25px";
    l.style.lineHeight = "25px";
    l.style.fontSize = "12px";
    l.style.textAlign = "center";
    l.style.whiteSpace = "nowrap";
    l.style.left = "35px";
    l.style.top = "5px";
    var E = document.createElement("div");
    E.style.position = "absolute";
    E.style.width = n + "px";
    E.style.height = ab + "px";
    E.style.left = s + "px";
    E.style.top = aq + "px";
    E.style.background = "url(" + SEGUtil.imageRootDir + "/arrow.png) no-repeat";
    d.appendChild(aj);
    d.appendChild(l);
    d.appendChild(E);
    var ak = document.createElement("div");
    ak.style.position = "absolute";
    ak.style.width = ah + "px";
    ak.style.height = Z + "px";
    ak.style.border = "1px solid gray";
    ak.style.background = "#FFFFFF";
    if (isIE) {
        ak.style.filter = "alpha(opacity=90)"
    } else {
        ak.style.opacity = "0.9"
    }
    var ae = document.createElement("div");
    ae.style.position = "absolute";
    ae.style.width = B + "px";
    ae.style.height = N + "px";
    ae.style.left = h + "px";
    ae.style.top = R + "px";
    ae.style.border = "1px solid gray";
    ae.style.background = "url(" + SEGUtil.imageRootDir + "/control/slow.png) no-repeat";
    ae.title = $slow;
    ae.style.cursor = "pointer";
    ae.onclick = function() {
        y()
    };
    var A = document.createElement("div");
    A.style.position = "absolute";
    A.style.width = B + "px";
    A.style.height = N + "px";
    A.style.left = (B + 2 * h) + "px";
    A.style.top = R + "px";
    A.style.border = "1px solid gray";
    A.style.background = "url(" + SEGUtil.imageRootDir + "/control/play.png) no-repeat";
    A.title = $play;
    A.style.cursor = "pointer";
    A.onclick = function() {
        if (q == 1) {
            b()
        } else {
            G()
        }
    };
    var D = document.createElement("div");
    D.style.position = "absolute";
    D.style.width = B + "px";
    D.style.height = N + "px";
    D.style.left = (B * 2 + 3 * h) + "px";
    D.style.top = R + "px";
    D.style.border = "1px solid gray";
    D.style.background = "url(" + SEGUtil.imageRootDir + "/control/quick.png)  no-repeat";
    D.title = $quick;
    D.style.cursor = "pointer";
    D.onclick = function() {
        a()
    };
    var f = document.createElement("div");
    f.style.position = "absolute";
    f.style.width = B + "px";
    f.style.height = N + "px";
    f.style.left = (B * 3 + 4 * h) + "px";
    f.style.top = R + "px";
    f.style.border = "1px solid gray";
    f.style.background = "url(" + SEGUtil.imageRootDir + "/control/stop.png)  no-repeat";
    f.style.cursor = "pointer";
    f.title = $stop;
    f.onclick = function() {
        u()
    };
    var I = document.createElement("div");
    I.style.position = "absolute";
    I.style.width = O + "px";
    I.style.height = ai + "px";
    I.style.background = "#A9A9A9";
    I.style.left = W + "px";
    I.style.top = ((N + 2 * R - ai) / 2) + "px";
    I.style.cursor = "pointer";
    var p = parseInt(ao / 2);
    var k = false;
    I.onmousedown = function() {
        k = true
    };
    I.onmouseup = function(aw) {
        if (k) {
            var at = window.event || aw;
            if (at.stopPropagation) {
                at.stopPropagation()
            } else {
                at.returnValue = false
            }
            var av = SEGUtil.getEventOffsetXY(aw, I);
            var au = av.x - p;
            if (au < 0) {
                au = 0
            } else {
                if (au > 250) {
                    au = 250
                }
            }
            x.style.left = au + "px";
            e(true)
        }
        k = false;
        w = false
    };
    var x = document.createElement("div");
    x.style.position = "absolute";
    x.style.width = "6px";
    x.style.height = (ai + 6) + "px";
    x.style.background = "#808080";
    x.style.left = "0px";
    x.style.top = "-3px";
    x.style.cursor = "pointer";
    if (isIE) {
        x.style.filter = "alpha(opacity=65)"
    } else {
        x.style.opacity = "0.65"
    }
    x.onmousedown = function() {
        w = true
    };
    var J = document.createElement("div");
    J.style.position = "absolute";
    J.style.width = O + "px";
    J.style.height = ai + "px";
    J.style.left = "0px";
    J.style.top = "0px";
    J.innerHTML = "0/0(0%)";
    J.style.textAlign = "center";
    J.style.fontSize = "12px";
    J.style.color = "white";
    J.style.lineHeight = ai + "px";
    I.onmousemove = function(aA) {
        var aC = SEGUtil.getEventOffsetXY(aA, I);
        if (aC.x > 500) {
            var az = window.event || aA;
            var au = az.target || az.srcElement;
            alert(au.outerHTML)
        }
        d.style.display = "block";
        d.style.left = (aC.x + W - o) + "px";
        var av = aC.x - p;
        if (av < 0) {
            av = 0
        } else {
            if (av > 250) {
                av = 250
            }
        }
        var aB = j.length;
        var ax = parseInt(aB * av / 250);
        var ay = ax - 1;
        if (ay <= -1) {
            l.innerHTML = "起点";
            aj.style.background = "url(" + SEGUtil.imageRootDir + "/start_24.png) no-repeat"
        } else {
            if (ay >= j.length) {
                l.innerHTML = "终点";
                aj.style.background = "url(" + SEGUtil.imageRootDir + "/end_24.png) no-repeat"
            } else {
                var at = j[ay];
                var aw = SEGUtil.getVehicleBackground(at.course, at.speed, at.gpsTime, at.isAlarm, at.status, at.isHistory);
                aj.style.background = aw;
                l.innerHTML = j[ay].gpsTime + " (" + ax + ")"
            }
        }
    };
    I.onmouseout = function(at) {
        d.style.display = "none"
    };
    var ag = document.createElement("div");
    ag.style.position = "absolute";
    ag.style.right = "0px";
    ag.style.top = "-1px";
    ag.style.width = P + "px";
    ag.style.height = Z + "px";
    ag.style.borderLeft = "1px solid gray";
    ag.style.background = "url(" + SEGUtil.imageRootDir + "/control/down_16.png) no-repeat 1px 12px";
    ag.style.cursor = "pointer";
    ag.title = $hide;
    ag.onclick = function() {
        ak.style.display = "none";
        Y.style.display = "block"
    };
    var M = document.createElement("div");
    M.style.position = "absolute";
    M.style.right = P + "px";
    M.style.top = "0px";
    M.style.width = "16px";
    M.style.height = "16px";
    M.style.background = "url(" + SEGUtil.imageRootDir + "/control/close.png) no-repeat";
    M.style.cursor = "pointer";
    M.title = $close;
    M.onclick = function() {
    	$.ligerDialog.confirm('确定要停止播放?', function(yes){
            if(yes === false){
            	return;
            } else{
            	af.closeHistory();
            	clearAllMarker();
	            if (CamionView) {
					CamionView.gpsInfoGrid.reload();
				}
            }
        })
//        var at = window.confirm("确定要停止播放?");
//        if (at) {
//            af.closeHistory();
//            if (CamionView) {
//				CamionView.gpsInfoGrid.reload();
//			}
//        }
    };
    this.closeHistory = function() {
        X();
        an.style.display = "none";
        if (H._history_callback3) {
            H._history_callback3()
        }
    };
    var L = -8;
    var c = 10;
    function a() {
        var at = ap;
        at++;
        T(at)
    }
    function y() {
        var at = ap;
        at--;
        T(at)
    }
    function T(av) {
        if (av == ap || av < L || av > c) {
            return
        }
        ap = av;
        var au;
        var at;
        if (av > 0) {
            at = parseInt(1000 / av);
            au = "x" + av
        } else {
            var aw = 2 - av;
            at = parseInt(1000 * aw);
            au = "1/" + aw
        }
        am.innerHTML = au;
        if (q == 1) {
            G()
        }
    }
    var am = document.createElement("div");
    am.style.position = "absolute";
    am.style.right = (P + 1) + "px";
    am.style.bottom = "1px";
    am.style.width = "26px";
    am.style.height = "15px";
    am.style.lineHeight = "15px";
    am.style.fontSize = "12px";
    am.innerHTML = "x1";
    am.style.background = "gray";
    am.style.color = "white";
    am.style.textAlign = "center";
    I.appendChild(J);
    I.appendChild(x);
    ak.appendChild(ae);
    ak.appendChild(A);
    ak.appendChild(D);
    ak.appendChild(f);
    ak.appendChild(I);
    ak.appendChild(ag);
    ak.appendChild(M);
    ak.appendChild(am);
    var Y = document.createElement("div");
    Y.style.position = "absolute";
    Y.style.left = ((ah - 32) / 2) + "px";
    Y.style.top = (Z - 13) + "px";
    Y.style.width = "32px";
    Y.style.height = "13px";
    Y.style.border = "1px solid gray";
    Y.style.display = "none";
    Y.title = $show;
    Y.style.cursor = "pointer";
    Y.style.background = "white";
    Y.onclick = function() {
        ak.style.display = "block";
        Y.style.display = "none"
    };
    var aa = document.createElement("div");
    aa.style.position = "absolute";
    aa.style.left = "8px";
    aa.style.top = "1px";
    aa.style.width = "16px";
    aa.style.height = "10px";
    aa.style.background = "url(" + SEGUtil.imageRootDir + "/control/up.png) no-repeat -4px -7px";
    Y.appendChild(aa);
    an.appendChild(ak);
    an.appendChild(Y);
    an.appendChild(d);
    this.show = function() {
        an.style.display = "block";
        ak.style.display = "block";
        Y.style.display = "none"
    };
    function e(au) {
        if (j == null) {
            return
        }
        var aw = j.length;
        if (aw == 0) {
            return
        }
        var aw = j.length;
        var ax = x.style.left;
        var at = parseInt(ax.substring(0, ax.length - 2));
        var av = parseInt(aw * at / 250);
        ac(av, au)
    }
    function ac(au, at) {
        U = au - 1;
        ar();
        if (at) {
            if (H._history_callback2 && U >= 0 && U < j.length) {
                var av = j[U];
                H._history_callback2(U, C, av)
            }
            H.playHistoryTo(U)
        }
    }
    function ar() {
        var aw = U + 1;
        var av = j.length;
        var at = 250 * aw / av;
        var au = Math.round(10000 * aw / av) / 100;
        x.style.left = at + "px";
        J.innerHTML = aw + "/" + av + "(" + au + "%)"
    }
    var t = function() {
        var at = (H.getDiv().offsetWidth - ah) / 2;
        an.style.left = at + "px"
    };
    this.resize = t;
    var w = false;
    var Q = function(at) {
        if (w) {
            e(true)
        }
        w = false
    };
    var z = function(aw) {
        if (!w) {
            return
        }
        var ax = SEGUtil.getOffsetXY(I, document.body);
        var au = SEGUtil.getEventOffsetXY(aw, document.body);
        var at = au.x - ax.x;
        var av = at - p;
        if (av < 0) {
            av = 0
        } else {
            if (av > 250) {
                av = 250
            }
        }
        x.style.left = av + "px";
        e(false)
    };
    this.initEvents = function() {
        if (window.addEventListener) {
            window.addEventListener("resize", t, false);
            window.addEventListener("mouseup", Q, false);
            window.addEventListener("mousemove", z, false)
        } else {
            if (window.attachEvent) {
                window.attachEvent("onresize", t);
                document.body.attachEvent("onmouseup", Q);
                document.body.attachEvent("onmousemove", z)
            }
        }
    };
    this.clearEvents = function() {
        if (window.addEventListener) {
            window.removeEventListener("resize", t, false);
            window.removeEventListener("mouseup", Q, false);
            window.removeEventListener("mousemove", z, false)
        } else {
            if (window.attachEvent) {
                window.detachEvent("onresize", t);
                document.body.detachEvent("onmouseup", Q);
                document.body.detachEvent("onmousemove", z)
            }
        }
    };
    var q = 0;
    var U = -1;
    var ap = 1;
    var C = null;
    var j = null;
    var F = null;
    this.getHistoryStatus = function() {
        return q
    };
    this.getHistoryIndex = function() {
        return U
    };
    this.getHistoryHead = function() {
        return C
    };
    this.getHistoryData = function() {
        return j
    };
    this.initData = function(au, at) {
        X();
        C = au;
        j = at;
        H.setHistoryData(au, j);
        ac(0);
        if (H._history_callback1) {
            H._history_callback1()
        }
    };
    function G() {
        var at;
        if (ap > 0) {
            at = parseInt(1000 / ap)
        } else {
            var au = 2 - ap;
            at = parseInt(1000 * au)
        }
        if (F != null) {
            window.clearInterval(F);
            F = null
        }
        q = 1;
        A.style.background = "url(" + SEGUtil.imageRootDir + "/control/pause.png) no-repeat";
        A.title = $pause;
        if (U >= j.length - 1) {
            m()
        } else {
            g();
            if (U >= j.length - 1) {
                m()
            } else {
                F = window.setInterval(function() {
                    al()
                },
                at)
            }
        }
    }
    this.play = G;
    function al() {
        g();
        if (U >= j.length - 1) {
            window.clearInterval(F);
            F = null;
            m()
        }
    }
    function g() {
        U++;
        if (H._history_callback2 && U >= 0 && U < j.length) {
            var at = j[U];
            H._history_callback2(U, C, at)
        }
        H.playHistoryTo(U);
        ar()
    }
    function m() {
        ac(0);
        q = 3;
        A.style.background = "url(" + SEGUtil.imageRootDir + "/control/play.png) no-repeat";
        A.title = $play;
        $.ligerDialog.success($playend);
    }
    function b() {
        if (F != null) {
            window.clearInterval(F);
            F = null
        }
        q = 2;
        A.style.background = "url(" + SEGUtil.imageRootDir + "/control/play.png) no-repeat";
        A.title = $play
    }
    function u() {
        if (F != null) {
            window.clearInterval(F);
            F = null
        }
        ac(0, true);
        q = 4;
        A.style.background = "url(" + SEGUtil.imageRootDir + "/control/play.png) no-repeat";
        A.title = $play
    }
    function X() {
        if (F != null) {
            window.clearInterval(F);
            F = null
        }
        H.resetHistory();
        j = null;
        C = null;
        q = 0;
        U = -1;
        T(1);
        A.style.background = "url(" + SEGUtil.imageRootDir + "/control/play.png) no-repeat";
        A.title = $play
    }
    this.initEvents()
};
function SEGNearBySearchDiv(g) {
    var C = this;
    var s = new SEGUtil.Div(0, 0, 310, 140).get();
    this.div = s;
    s.style.background = "white";
    var q = 24;
    var k = 10;
    var m = 10;
    var y = new SEGUtil.Div(0, m, null, q).get();
    y.style.width = "100%";
    var f = 14;
    var d = 22;
    var B = 60;
    var c = new SEGUtil.Div(k, -2, f, d).get();
    c.style.background = "url(" + SEGUtil.imageRootDir + "/markers_point.png) -28px -16px no-repeat";
    var t = document.createElement("a");
    t.style.position = "absolute";
    t.style.left = (k + f + 2) + "px";
    t.innerHTML = $navstartp;
    t.href = "javascript:void(0)";
    t.onclick = function() {
        g(2)
    };
    var l = new SEGUtil.Div(k + B + f + 2, -2, f, d).get();
    l.style.background = "url(" + SEGUtil.imageRootDir + "/markers_star.png) -28px -16px no-repeat";
    var z = document.createElement("a");
    z.style.position = "absolute";
    z.style.left = (k + B + 2 * f + 4) + "px";
    z.style.top = "0";
    z.innerHTML = $navendp;
    z.href = "javascript:void(0)";
    z.onclick = function() {
        g(3)
    };
    y.appendChild(c);
    y.appendChild(t);
    y.appendChild(l);
    y.appendChild(z);
    var x = 90;
    var n = new SEGUtil.Div(k, q + 2 * m, x, q).get();
    n.innerHTML = $searchr + "(" + $cb + ")";
    n.style.lineHeight = q + "px";
    n.style.whiteSpace = "nowrap";
    var b = document.createElement("input");
    b.type = "text";
    b.maxLength = 10;
    b.value = "1000";
    b.style.position = "absolute";
    b.style.left = (2 * k + x) + "px";
    b.style.top = q + 2 * m + "px";
    b.style.width = "120px";
    b.style.height = q + "px";
    b.onkeypress = function(D) {
        var E = window.event || D;
        if (E.keyCode == 13) {
            o(w.value)
        }
    };
    var j = new SEGUtil.Div(0, 2 * q + 3 * m, null, q).get();
    j.style.width = "100%";
    var h = document.createElement("a");
    h.style.margin = "0 0 0 " + k + "px";
    h.innerHTML = $hotel;
    h.href = "javascript:void(0)";
    h.onclick = function() {
        o($hotel)
    };
    var v = document.createElement("a");
    v.style.margin = "0 0 0 10px";
    v.innerHTML = $restaurant;
    v.href = "javascript:void(0)";
    v.onclick = function() {
        o($restaurant)
    };
    var e = document.createElement("a");
    e.style.margin = "0 0 0 10px";
    e.innerHTML = $bank;
    e.href = "javascript:void(0)";
    e.onclick = function() {
        o($bank)
    };
    var r = document.createElement("a");
    r.style.margin = "0 0 0 10px";
    r.innerHTML = $atm;
    r.href = "javascript:void(0)";
    r.onclick = function() {
        o($atm)
    };
    var p = document.createElement("a");
    p.style.margin = "0 0 0 10px";
    p.innerHTML = $hospital;
    p.href = "javascript:void(0)";
    p.onclick = function() {
        o($hospital)
    };
    var i = document.createElement("a");
    i.style.margin = "0 0 0 10px";
    i.innerHTML = $carpark;
    i.href = "javascript:void(0)";
    i.onclick = function() {
        o($carpark)
    };
    var A = document.createElement("a");
    A.style.margin = "0 0 0 10px";
    A.innerHTML = $stations;
    A.href = "javascript:void(0)";
    A.onclick = function() {
        o($stations)
    };
    j.appendChild(h);
    j.appendChild(v);
    j.appendChild(e);
    j.appendChild(r);
    j.appendChild(p);
    j.appendChild(i);
    j.appendChild(A);
    var a = 240;
    var w = document.createElement("input");
    w.type = "text";
    w.style.position = "absolute";
    w.style.left = k + "px";
    w.style.top = (3 * q + 4 * m) + "px";
    w.style.width = a + "px";
    w.style.height = q + "px";
    w.onkeypress = function(D) {
        var E = window.event || D;
        if (E.keyCode == 13) {
            o(w.value)
        }
    };
    var u = new SEGUtil.Div(2 * k + a, 3 * q + 4 * m, 48, 26).get();
    u.innerHTML = $search;
    u.style.cursor = "pointer";
    u.style.textAlign = "center";
    u.style.lineHeight = q + "px";
    u.style.background = "url(" + SEGUtil.imageRootDir + "/bg.png) 0 -87px no-repeat";
    u.onmouseover = function() {
        u.style.backgroundPosition = "-52px -87px"
    };
    u.onmouseout = function() {
        u.style.backgroundPosition = "0 -87px"
    };
    u.onclick = function() {
        o(w.value)
    };
    function o(E) {
        if (E.length == 0) {
            w.focus();
            return
        }
        if (g) {
            var D = parseInt(b.value);
            if (isNaN(D) || D <= 0) {
                alert($raderror);
                return
            }
            g(1, {
                formDiv: C,
                radius: D,
                name: E
            })
        }
    }
    s.appendChild(y);
    s.appendChild(n);
    s.appendChild(b);
    s.appendChild(j);
    s.appendChild(w);
    s.appendChild(u);
    this.resetForm = function() {
        b.value = "1000";
        w.value = "";
        w.focus()
    }
}
var BaiduConverter = {
    x_pi: 3.141592653589793 * 3000 / 180,
    encrypt: function(i, c) {
        var g = i,
        f = c;
        var d = Math.sqrt(g * g + f * f) + 0.00002 * Math.sin(f * this.x_pi);
        var a = Math.atan2(f, g) + 0.000003 * Math.cos(g * this.x_pi);
        var e = d * Math.cos(a) + 0.0065;
        var b = d * Math.sin(a) + 0.006;
        var h = new Point();
        h.setX(e);
        h.setY(b);
        return h
    },
    decrypt: function(e, b) {
        var g = e - 0.0065,
        f = b - 0.006;
        var d = Math.sqrt(g * g + f * f) - 0.00002 * Math.sin(f * this.x_pi);
        var a = Math.atan2(f, g) - 0.000003 * Math.cos(g * this.x_pi);
        var i = d * Math.cos(a);
        var c = d * Math.sin(a);
        var h = new Point();
        h.setX(i);
        h.setY(c);
        return h
    }
};

function Convertor(ak) {
    this.stepCount = 100;
    this.pointCount = [];
    this.Result = [];
    this.NoisIndex = [];
    this.Time = new Date();
    this.AK = ak;
    this.M_PI = 3.14159265358979324;
    this.A = 6378245.0;
    this.EE = 0.00669342162296594323;
    this.X_PI = this.M_PI * 3000.0 / 180.0;
}
Convertor.prototype.outofChine = function(p) {
    if (p.lng < 72.004 || p.lng > 137.8347) {
        return true;
    }
    if (p.lat < 0.8293 || p.lat > 55.8271) {
        return true;
    }
    return false;
}
;
Convertor.prototype.WGS2GCJ_lat = function(x, y) {
    var ret1 = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
    ret1 += (20.0 * Math.sin(6.0 * x * this.M_PI) + 20.0 * Math.sin(2.0 * x * this.M_PI)) * 2.0 / 3.0;
    ret1 += (20.0 * Math.sin(y * this.M_PI) + 40.0 * Math.sin(y / 3.0 * this.M_PI)) * 2.0 / 3.0;
    ret1 += (160.0 * Math.sin(y / 12.0 * this.M_PI) + 320 * Math.sin(y * this.M_PI / 30.0)) * 2.0 / 3.0;
    return ret1;
}
;
Convertor.prototype.WGS2GCJ_lng = function(x, y) {
    var ret2 = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
    ret2 += (20.0 * Math.sin(6.0 * x * this.M_PI) + 20.0 * Math.sin(2.0 * x * this.M_PI)) * 2.0 / 3.0;
    ret2 += (20.0 * Math.sin(x * this.M_PI) + 40.0 * Math.sin(x / 3.0 * this.M_PI)) * 2.0 / 3.0;
    ret2 += (150.0 * Math.sin(x / 12.0 * this.M_PI) + 300.0 * Math.sin(x / 30.0 * this.M_PI)) * 2.0 / 3.0;
    return ret2;
}
;
Convertor.prototype.WGS2GCJ = function(poi) {
    if (this.outofChine(poi)) {
        return;
    }
    var poi2 = {};
    var dLat = this.WGS2GCJ_lat(poi.lng - 105.0, poi.lat - 35.0);
    var dLon = this.WGS2GCJ_lng(poi.lng - 105.0, poi.lat - 35.0);
    var radLat = poi.lat / 180.0 * this.M_PI;
    var magic = Math.sin(radLat);
    magic = 1 - this.EE * magic * magic;
    var sqrtMagic = Math.sqrt(magic);
    dLat = (dLat * 180.0) / ((this.A * (1 - this.EE)) / (magic * sqrtMagic) * this.M_PI);
    dLon = (dLon * 180.0) / (this.A / sqrtMagic * Math.cos(radLat) * this.M_PI);
    poi2.lat = poi.lat + dLat;
    poi2.lng = poi.lng + dLon;
    return poi2;
}
;
Convertor.prototype.GCJ2BD09 = function(poi) {
    var poi2 = {};
    var x = poi.lng
      , y = poi.lat;
    var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * this.X_PI);
    var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * this.X_PI);
    poi2.lng = z * Math.cos(theta) + 0.0065;
    poi2.lat = z * Math.sin(theta) + 0.006;
    return poi2;
}
;
/**
* WGS->百度坐标系
*/
Convertor.prototype.WGS2BD09 = function(poi) {
    //WGS->GCJ
    var poi2 = this.WGS2GCJ(poi);
    if (typeof poi2 === "undefined") {
        return;
    }
    //GCJ->百度坐标系
    return this.GCJ2BD09(poi2);
}

var Converter = function() {
    this.casm_rr = 0;
    this.casm_t1 = 0;
    this.casm_t2 = 0;
    this.casm_x1 = 0;
    this.casm_y1 = 0;
    this.casm_x2 = 0;
    this.casm_y2 = 0;
    this.casm_f = 0;
    var a = this;
    this.me = this;
    this.yj_sin2 = function(b) {
        var f;
        var e;
        var c;
        var d;
        var g;
        c = 0;
        if (b < 0) {
            b = -b;
            c = 1
        }
        g = parseInt(b / 6.28318530717959);
        f = b - g * 6.28318530717959;
        if (f > 3.141592653589793) {
            f = f - 3.141592653589793;
            if (c == 1) {
                c = 0
            } else {
                if (c == 0) {
                    c = 1
                }
            }
        }
        b = f;
        e = b;
        d = b;
        f = f * f;
        d = d * f;
        e = e - d * 0.166666666666667;
        d = d * f;
        e = e + d * 0.00833333333333333;
        d = d * f;
        e = e - d * 0.000198412698412698;
        d = d * f;
        e = e + d * 0.00000275573192239859;
        d = d * f;
        e = e - d * 2.50521083854417e-8;
        if (c == 1) {
            e = -e
        }
        return e
    };
    this.Transform_yj5 = function(b, d) {
        var c;
        c = 300 + 1 * b + 2 * d + 0.1 * b * b + 0.1 * b * d + 0.1 * Math.sqrt(Math.sqrt(b * b));
        c = c + (20 * a.yj_sin2(18.849555921538762 * b) + 20 * a.yj_sin2(6.283185307179588 * b)) * 0.6667;
        c = c + (20 * a.yj_sin2(3.141592653589794 * b) + 40 * a.yj_sin2(1.047197551196598 * b)) * 0.6667;
        c = c + (150 * a.yj_sin2(0.2617993877991495 * b) + 300 * a.yj_sin2(0.1047197551196598 * b)) * 0.6667;
        return c
    };
    this.Transform_yjy5 = function(b, d) {
        var c;
        c = -100 + 2 * b + 3 * d + 0.2 * d * d + 0.1 * b * d + 0.2 * Math.sqrt(Math.sqrt(b * b));
        c = c + (20 * a.yj_sin2(18.849555921538762 * b) + 20 * a.yj_sin2(6.283185307179588 * b)) * 0.6667;
        c = c + (20 * a.yj_sin2(3.141592653589794 * d) + 40 * a.yj_sin2(1.047197551196598 * d)) * 0.6667;
        c = c + (160 * a.yj_sin2(0.2617993877991495 * d) + 320 * a.yj_sin2(0.1047197551196598 * d)) * 0.6667;
        return c
    };
    this.Transform_jy5 = function(b, f) {
        var g;
        var c;
        var d;
        c = 6378245;
        d = 0.00669342;
        g = Math.sqrt(1 - d * a.yj_sin2(b * 0.0174532925199433) * a.yj_sin2(b * 0.0174532925199433));
        g = (f * 180) / (c / g * Math.cos(b * 0.0174532925199433) * 3.1415926);
        return g
    };
    this.Transform_jyj5 = function(c, h) {
        var b;
        var d;
        var f;
        var g;
        d = 6378245;
        f = 0.00669342;
        g = 1 - f * a.yj_sin2(c * 0.0174532925199433) * a.yj_sin2(c * 0.0174532925199433);
        b = (d * (1 - f)) / (g * Math.sqrt(g));
        return (h * 180) / (b * 3.1415926)
    };
    this.random_yj = function() {
        var c;
        var d = 314159269;
        var b = 453806245;
        a.casm_rr = d * a.casm_rr + b;
        c = parseInt(a.casm_rr / 2);
        a.casm_rr = a.casm_rr - c * 2;
        a.casm_rr = a.casm_rr / 2;
        return (a.casm_rr)
    };
    this.IniCasm = function(e, b, d) {
        var c;
        a.casm_t1 = e;
        a.casm_t2 = e;
        c = parseInt(e / 0.357);
        a.casm_rr = e - c * 0.357;
        if (e == 0) {
            a.casm_rr = 0.3
        }
        a.casm_x1 = b;
        a.casm_y1 = d;
        a.casm_x2 = b;
        a.casm_y2 = d;
        a.casm_f = 3
    };
    this.wgtochina_lb = function(l, j, h, e, q, d) {
        var o;
        var n;
        var c;
        var g;
        var m;
        var i;
        var f;
        var k;
        var b;
        var p = null;
        if (e > 5000) {
            return p
        }
        g = j;
        g = g / 3686400;
        m = h;
        m = m / 3686400;
        if (g < 72.004) {
            return p
        }
        if (g > 137.8347) {
            return p
        }
        if (m < 0.8293) {
            return p
        }
        if (m > 55.8271) {
            return p
        }
        if (l == 0) {
            a.IniCasm(d, j, h);
            p = new Point();
            p.setLatitude(j);
            p.setLongitude(h);
            return p
        }
        a.casm_t2 = d;
        f = (a.casm_t2 - a.casm_t1) / 1000;
        if (f <= 0) {
            a.casm_t1 = a.casm_t2;
            a.casm_f = a.casm_f + 1;
            a.casm_x1 = a.casm_x2;
            a.casm_f = a.casm_f + 1;
            a.casm_y1 = a.casm_y2;
            a.casm_f = a.casm_f + 1
        } else {
            if (f > 120) {
                if (a.casm_f == 3) {
                    a.casm_f = 0;
                    a.casm_x2 = j;
                    a.casm_y2 = h;
                    k = a.casm_x2 - a.casm_x1;
                    b = a.casm_y2 - a.casm_y1;
                    i = Math.sqrt(k * k + b * b) / f;
                    if (i > 3185) {
                        return (p)
                    }
                }
                a.casm_t1 = a.casm_t2;
                a.casm_f = a.casm_f + 1;
                a.casm_x1 = a.casm_x2;
                a.casm_f = a.casm_f + 1;
                a.casm_y1 = a.casm_y2;
                a.casm_f = a.casm_f + 1
            }
        }
        o = a.Transform_yj5(g - 105, m - 35);
        n = a.Transform_yjy5(g - 105, m - 35);
        c = e;
        o = o + c * 0.001 + a.yj_sin2(d * 0.0174532925199433) + a.random_yj();
        n = n + c * 0.001 + a.yj_sin2(d * 0.0174532925199433) + a.random_yj();
        p = new Point();
        p.setX(((g + a.Transform_jy5(m, o)) * 3686400));
        p.setY(((m + a.Transform_jyj5(m, n)) * 3686400));
        return p
    };
    this.isValid = function(b) {
        var c = 3600;
        var d = new Date();
        if (d.getTime() / 1000 - 1253525356 >= b * 24 * c) {
            return false
        } else {
            return true
        }
    };
    this.getEncryPoint = function(i, g) {
        var l;
        var e, c;
        var h, b;
        e = i * 3686400;
        h = g * 3686400;
        var k = 0;
        var f = 0;
        var j = 0;
        l = a.wgtochina_lb(1, parseInt(e), parseInt(h), parseInt(j), parseInt(k), parseInt(f));
        if (l == null) {
            var d = new Point();
            d.setX(i);
            d.setY(g);
            return d
        }
        c = l.getX();
        b = l.getY();
        c = c / 3686400;
        b = b / 3686400;
        l = new Point();
        l.setX(c);
        l.setY(b);
        return l
    };
    this.getEncryCoord = function(f, g) {
        if (g) {
            var j = parseFloat(f.split(",")[0]);
            var h = parseFloat(f.split(",")[1]);
            var m = new Point();
            var d, c;
            var i, b;
            d = j * 3686400;
            i = h * 3686400;
            var l = 0;
            var e = 0;
            var k = 0;
            m = a.wgtochina_lb(1, parseInt(d), parseInt(i), parseInt(k), parseInt(l), parseInt(e));
            c = m.getX();
            b = m.getY();
            c = c / 3686400;
            b = b / 3686400;
            return c + "," + b
        } else {
            return ""
        }
    }
};
var Deconverter = {
    decode0: function(b, a) {
        var d = new Converter();
        var g = d.getEncryPoint(b, a);
        var i = g.x - b;
        var h = g.y - a;
        var e = b - i;
        var c = a - h;
        var f = new Point();
        f.setX(e);
        f.setY(c);
        return f
    },
    decode: function(d, b) {
        var n = this.decode0(d, b);
        var g = n.x;
        var f = n.y;
        var i = 0;
        var e = 10;
        var l = 10000000;
        while (true) {
            if (i > e) {
                break
            }
            var k = new Converter();
            var h = k.getEncryPoint(g, f);
            var q = h.x - d;
            var o = h.y - b;
            var m = Math.abs(h.x * l - d * l);
            var j = Math.abs(h.y * l - b * l);
            if (m < 5 && j < 5) {
                break
            }
            g -= q;
            f -= o;
            i++
        }
        var a = new Point();
        a.setX(g);
        a.setY(f);
        return a
    }
};
var Point = function() {
    this.longitude;
    this.latitude;
    this.x;
    this.y;
    this.setX = function(a) {
        this.x = a
    };
    this.getX = function() {
        return this.x
    };
    this.setY = function(a) {
        this.y = a
    };
    this.getY = function() {
        return this.y
    };
    this.setLongitude = function(a) {
        this.longitude = a
    };
    this.setLatitude = function(a) {
        this.latitude = a
    };
    this.getLongitude = function() {
        return this.longitude
    };
    this.getLatitude = function() {
        return this.latitude
    }
};
var BMapLib = window.BMapLib = BMapLib || {};
(function() {
	var c, a = c = a || {
		version : "1.3.9"
	};
	a.guid = "$BAIDU$";
	(function() {
		a.dom = a.dom || {};
		a.event = a.event || {};
		a.lang = a.lang || {};
		a.browser = a.browser || {};
		a.dom.addClass = function(p, q) {
			p = a.dom.g(p);
			var k = q.split(/\s+/), j = p.className, o = " " + j + " ", n = 0, m = k.length;
			for (; n < m; n++) {
				if (o.indexOf(" " + k[n] + " ") < 0) {
					j += (j ? " " : "") + k[n]
				}
			}
			p.className = j;
			return p
		};
		a.addClass = a.dom.addClass;
		a.dom.removeClass = function(p, q) {
			p = a.dom.g(p);
			var n = p.className.split(/\s+/), r = q.split(/\s+/), l, k = r.length, m, o = 0;
			for (; o < k; ++o) {
				for (m = 0, l = n.length; m < l; ++m) {
					if (n[m] == r[o]) {
						n.splice(m, 1);
						break
					}
				}
			}
			p.className = n.join(" ");
			return p
		};
		a.removeClass = a.dom.removeClass;
		a.dom.getComputedStyle = function(k, j) {
			k = a.dom._g(k);
			var m = a.dom.getDocument(k), l;
			if (m.defaultView && m.defaultView.getComputedStyle) {
				l = m.defaultView.getComputedStyle(k, null);
				if (l) {
					return l[j] || l.getPropertyValue(j)
				}
			}
			return ""
		};
		a.dom.getStyle = function(k, j) {
			var m = a.dom;
			k = m.g(k);
			var l = k.style[j] || (k.currentStyle ? k.currentStyle[j] : "")
					|| m.getComputedStyle(k, j);
			return l
		};
		a.getStyle = a.dom.getStyle;
		a.dom.getDocument = function(j) {
			j = a.dom.g(j);
			return j.nodeType == 9 ? j : j.ownerDocument || j.document
		};
		a.dom.g = function(j) {
			if ("string" == typeof j || j instanceof String) {
				return document.getElementById(j)
			} else {
				if (j && j.nodeName && (j.nodeType == 1 || j.nodeType == 9)) {
					return j
				}
			}
			return null
		};
		a.g = a.G = a.dom.g;
		a.dom._g = function(j) {
			if (a.lang.isString(j)) {
				return document.getElementById(j)
			}
			return j
		};
		a._g = a.dom._g;
		a.lang.isString = function(j) {
			return "[object String]" == Object.prototype.toString.call(j)
		};
		a.isString = a.lang.isString;
		a.event._listeners = a.event._listeners || [];
		a.event.on = function(k, n, p) {
			n = n.replace(/^on/i, "");
			k = a.dom._g(k);
			var o = function(r) {
				p.call(k, r)
			}, j = a.event._listeners, m = a.event._eventFilter, q, l = n;
			n = n.toLowerCase();
			if (m && m[n]) {
				q = m[n](k, n, o);
				l = q.type;
				o = q.listener
			}
			if (k.addEventListener) {
				k.addEventListener(l, o, false)
			} else {
				if (k.attachEvent) {
					k.attachEvent("on" + l, o)
				}
			}
			j[j.length] = [k, n, p, o, l];
			return k
		};
		a.on = a.event.on;
		a.event.un = function(l, o, k) {
			l = a.dom._g(l);
			o = o.replace(/^on/i, "").toLowerCase();
			var r = a.event._listeners, m = r.length, n = !k, q, p, j;
			while (m--) {
				q = r[m];
				if (q[1] === o && q[0] === l && (n || q[2] === k)) {
					p = q[4];
					j = q[3];
					if (l.removeEventListener) {
						l.removeEventListener(p, j, false)
					} else {
						if (l.detachEvent) {
							l.detachEvent("on" + p, j)
						}
					}
					r.splice(m, 1)
				}
			}
			return l
		};
		a.un = a.event.un;
		if (/msie (\d+\.\d)/i.test(navigator.userAgent)) {
			a.browser.ie = a.ie = document.documentMode || +RegExp["\x241"]
		}
		a.platform = a.platform || {};
		a.platform.isIphone = /iphone/i.test(navigator.userAgent);
		a.platform.isAndroid = /android/i.test(navigator.userAgent);
		a.platform.isIpad = /ipad/i.test(navigator.userAgent);
		a.isMobile = function() {
			return !!(a.platform.isIphone || a.platform.isIpad || a.platform.isAndroid)
		}
	})();
	var h = a.isMobile() ? "_mobile" : "_deskTop";
	var g = BMapLib.TrafficControl = function(j) {
		this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
		this.defaultOffset = new BMap.Size(10, 10);
		this.showPanel = j && j.showPanel
	};
	g.prototype = new BMap.Control();
	g.prototype.initialize = function(l) {
		var j = d("div", {
					title : "显示交通流量",
					id : "tcBtn",
					"class" : "maplibTcBtn" + h + " maplibTcBtnOff" + h
				});
		l.getContainer().appendChild(j);
		this._map = l;
		this._popUpDiv(this, j);
		var k = this;
		this.btn = j;
		return j
	};
	g.prototype._popUpDiv = function(F, w) {
		var F = this;
		var x = ["查看实时路况", "流量预测"];
		var l = ["查看流量预测", "实时路况"];
		var A = true;
		this.bShow = false;
		var D = this;
		D._bind = false;
		b(w, "afterEnd", u());
		b(w, "afterEnd", H());
		var B = a.g("tcViewPrediction");
		var t = a.g("tcPredition");
		var G = a.g("tcTitle");
		var n = a.g("maplibTcDay");
		var y = a.g("tcNow");
		var p = a.g("tcWrap");
		var v = a.g("tcTimeBox");
		var z = a.g("tcUpdate");
		var j = ["一", "二", "三", "四", "五", "六", "日"];
		var s = new E(F);
		this.show = function() {
			k();
			F.bShow = true;
			a.dom.removeClass(w, "maplibTcBtnOff" + h)
		};
		this.hide = function() {
			F.bShow = false;
			a.dom.addClass(w, "maplibTcBtnOff" + h);
			if (a.isMobile()) {
				a.dom.addClass("tcWrap_mobile", "maplibTfctrHide")
			} else {
				a.dom.addClass("tcWrap", "maplibTcHide");
				a.dom.addClass("tcPredition", "maplibTcHide")
			}
			F.hideTraffic()
		};
		this.isbShow = function() {
			return F.bShow
		};
		this.setPopOffset = function(J) {
			var L = 24;
			var K = J.height + L + "px";
			var I = J.width + "px";
			switch (F.getAnchor()) {
				case BMAP_ANCHOR_TOP_LEFT :
					p.style.top = K;
					p.style.left = I;
					break;
				case BMAP_ANCHOR_TOP_RIGHT :
					p.style.top = K;
					p.style.right = I;
					break;
				case BMAP_ANCHOR_BOTTOM_RIGHT :
					p.style.bottom = K;
					p.style.right = I;
					break;
				case BMAP_ANCHOR_BOTTOM_LEFT :
					p.style.bottom = K;
					p.style.left = I;
					break
			}
		};
		var r = a.isMobile() ? "ontouchend" : "onclick";
		a.event.on(w, r, function() {
					o()
				});
		a.event.on("tcClose", "click", function(I) {
					o()
				});
		function o() {
			if (!D.isbShow()) {
				D.setPopOffset(F.getOffset());
				D.show()
			} else {
				D.hide()
			}
		}
		function k() {
			n.innerHTML = "更新时间";
			G.innerHTML = l[1];
			B.innerHTML = l[0];
			a.dom.addClass(t, "maplibTcHide");
			z.style.display = "block";
			A = true;
			if (a.isMobile()) {
				if (F.showPanel) {
					a.dom.removeClass("tcWrap_mobile", "maplibTfctrHide")
				}
			} else {
				a.dom.removeClass("tcWrap", "maplibTcHide")
			}
			var K = a.g("tcWeek").getElementsByTagName("a");
			for (var J = 0; J < 7; J++) {
				K[J].className = ""
			}
			var I = "http://its.map.baidu.com:8002/traffic/GetCurrentTime?callback=BMapLib.TrafficControl.getTime&";
			e(I + (new Date()).getTime(), L);
			if (F.timer) {
				clearInterval(F.timer)
			}
			F.timer = setInterval(function() {
						e(I + (new Date()).getTime(), function() {
									var N = g.curTime;
									var M = N.getHours();
									var O = (M < 10 ? ("0" + M) : M)
											+ ":"
											+ (N.getMinutes() < 10 ? ("0" + N
													.getMinutes()) : N
													.getMinutes());
									y.innerHTML = O;
									F.hideTraffic();
									F.showTraffic()
								})
					}, 1000 * 3 * 60);
			function L() {
				var N = g.curTime;
				var M = N.getHours();
				if (!D._bind) {
					C(F);
					m(F);
					D._bind = true
				}
				var O = (M < 10 ? ("0" + M) : M)
						+ ":"
						+ (N.getMinutes() < 10 ? ("0" + N.getMinutes()) : N
								.getMinutes());
				y.innerHTML = O;
				F.hour = M;
				F.weekday = N.getDay() == 0 ? 7 : N.getDay();
				F.time = O;
				s.setBarTime(M)
			}
		}
		function H() {
			//return '<div class="maplibTfctr maplibTfctrHide" id="tcWrap_mobile" style="position: absolute; z-index: 10; -webkit-text-size-adjust: none; bottom: 17px; right: auto; top: auto; left:50%;margin-left:-4.5em;"><div class="maplibTfctr_l"></div><div class="maplibTfctr_c">实时路况</div><div class="maplibTfctr_status"><span class="maplibR">堵</span><span class="maplibY">缓</span><span class="maplibG">畅</span></div></div>'
			return '<div class="maplibTfctr maplibTfctrHide" id="tcWrap_mobile" style="position: absolute; z-index: 10; -webkit-text-size-adjust: none; bottom: 17px; right: auto; top: auto; left:50%;margin-left:-4.5em;"><div class="maplibTfctr_l"></div><div class="maplibTfctr_c"></div><div class="maplibTfctr_status"><span class="maplibR"></span><span class="maplibY"></span><span class="maplibG"></span></div></div>'
		}
//		function u() {
//			var I = ['<div class="maplibTc maplibTcHide" id="tcWrap">'];
//			I.push('<div class="maplibTcColor" id="tcTitle">实时路况</div>');
//			I.push('<div id="tcRealTime">');
//			I
//					.push('<div class="maplibTcTime"><span id="maplibTcDay" class="maplibTcCurTime">更新时间</span><span><span class="maplibTcColon">：&nbsp;</span><span class="maplibTcCurTime" id="tcNow"></span><span title="更新" id="tcUpdate" class="maplibTcUpdate"></span> <a href="javascript:void(0)" class="maplibTcView" id="tcViewPrediction">查看流量预测</a><button class="maplibTcClose" id="tcClose"></button></div></div>');
//			I.push('<div id="tcPredition" class="maplibTcHide">');
//			I
//					.push('<div class="maplibTcWeekDay"><span>星期</span><ul id="tcWeek"><li><a lang="1" href="javascript:void(0)">一</a></li><li><a lang="2" href="javascript:void(0)">二</a></li><li><a lang="3" href="javascript:void(0)">三</a></li><li><a lang="4" href="javascript:void(0)">四</a></li><li><a lang="5" href="javascript:void(0)">五</a></li><li><a lang="6" href="javascript:void(0)">六</a></li><li><a lang="7" href="javascript:void(0)">日</a></li></ul></div>');
//			I.push('<div><div class="maplibTcRuleTxt">时间</div>');
//			I.push('<div class="maplibTcRule">');
//			I
//					.push('<div><div class="maplibTcTimeBox" id="tcTimeBox">20:00</div></div>');
//			I.push('<div class="maplibTcTimeline" >');
//			I.push('<div class="maplibTcTimelinePrev" id="tcPrev"></div>');
//			I.push('<div class="maplibTcTimeMove" id="tcMove"></div>');
//			I.push('<div class="maplibTcTimelineNext" id="tcNext"></div>');
//			I.push("</div></div></div>");
//			I
//					.push('<div class="maplibTcClear" style="text-align: center; color: #ccc;">（基于历史流量统计预测 仅供参考）</div>');
//			I.push("</div></div></div>");
//			return I.join("")
//		}
		function u() {
			var I = ['<div class="maplibTc maplibTcHide" id="tcWrap">'];
			I.push('<div class="maplibTcColor" id="tcTitle"></div>');
			I.push('<div id="tcRealTime">');
			I.push('<div class="maplibTcTime"><span id="maplibTcDay" class="maplibTcCurTime"></span><span><span class="maplibTcColon"></span><span class="maplibTcCurTime" id="tcNow"></span><span title="" id="tcUpdate" class="maplibTcUpdate"></span> <a href="javascript:void(0)" class="maplibTcView" id="tcViewPrediction"></a><button class="maplibTcClose" id="tcClose"></button></div></div>');
			I.push('<div id="tcPredition" class="maplibTcHide">');
			I.push('<div class="maplibTcWeekDay"><span></span><ul id="tcWeek"><li><a lang="1" href="javascript:void(0)"></a></li><li><a lang="2" href="javascript:void(0)"></a></li><li><a lang="3" href="javascript:void(0)"></a></li><li><a lang="4" href="javascript:void(0)"></a></li><li><a lang="5" href="javascript:void(0)"></a></li><li><a lang="6" href="javascript:void(0)"></a></li><li><a lang="7" href="javascript:void(0)"></a></li></ul></div>');
			I.push('<div><div class="maplibTcRuleTxt"></div>');
			I.push('<div class="maplibTcRule">');
			I.push('<div><div class="maplibTcTimeBox" id="tcTimeBox"></div></div>');
			I.push('<div class="maplibTcTimeline" >');
			I.push('<div class="maplibTcTimelinePrev" id="tcPrev"></div>');
			I.push('<div class="maplibTcTimeMove" id="tcMove"></div>');
			I.push('<div class="maplibTcTimelineNext" id="tcNext"></div>');
			I.push("</div></div></div>");
			I.push('<div class="maplibTcClear" style="text-align: center; color: #ccc;"></div>');
			I.push("</div></div></div>");
			return I.join("")
		}
		function C(J) {
			a.event.on("tcViewPrediction", "click", function() {
						if (A) {
							I()
						} else {
							k()
						}
					});
			function I() {
				if (J.timer) {
					clearInterval(J.timer)
				}
				G.innerHTML = x[1];
				B.innerHTML = x[0];
				a.dom.removeClass(t, "maplibTcHide");
				z.style.display = "none";
				A = false;
				n.innerHTML = "星期" + j[J.weekday - 1];
				y.innerHTML = v.innerHTML;
				J.showTraffic({
							predictDate : {
								hour : J.hour,
								weekday : J.weekday
							}
						})
			}
			a.event.on("tcUpdate", "click", function() {
						k()
					})
		}
		function q() {
			n.innerHTML = "星期" + j[F.weekday - 1];
			y.innerHTML = v.innerHTML
		}
		function m(I) {
			a.event.on("tcWeek", "onclick", function(L) {
						var K = L.target || L.srcElement;
						if (K.tagName.toLowerCase() == "a") {
							var M = a.g("tcWeek").getElementsByTagName("a");
							for (var J = 0; J < 7; J++) {
								M[J].className = ""
							}
							a.dom.addClass(K, "maplibTcOn");
							I.weekday = (parseInt(f(K, "lang"), 10));
							q();
							I.showTraffic({
										predictDate : {
											hour : I.hour,
											weekday : I.weekday
										}
									})
						}
					})
		}
		function E(M) {
			var J;
			var O = a.g("tcMove");
			function N(R) {
				a.on(document, "onmousemove", Q);
				a.on(document, "onmouseup", I);
				if (R && R.preventDefault) {
					R.preventDefault()
				} else {
					window.event.returnValue = false
				}
				return false
			}
			function Q(U) {
				var R = U.clientX || U.x;
				var T = i(a.G("tcPrev")).left + 9;
				var S = R - T - 4;
				if (S < 0) {
					S = 0
				}
				if (S > 165) {
					S = 165
				}
				if (a.browser.ie <= 6) {
					O.style.marginLeft = (S * 0.53) + "px"
				} else {
					O.style.marginLeft = S + "px"
				}
				v.style.marginLeft = (S) + "px";
				L()
			}
			function I() {
				a.un(document, "onmousemove", Q);
				a.un(document, "onmouseup", I);
				M.showTraffic({
							predictDate : {
								hour : M.hour,
								weekday : M.weekday
							}
						})
			}
			a.on(O, "onmousedown", N);
			a.on("tcPrev", "click", function() {
						P("prev")
					});
			a.on("tcNext", "click", function() {
						P("next")
					});
			function P(R) {
				var S = v;
				var T = parseInt(a.dom.getStyle("tcTimeBox", "marginLeft"));
				var U = Math.ceil((T - 4) * 24 / 165);
				K(R == "next" ? (U + 1) : (U - 1))
			}
			this.setBarTime = function(R) {
				K(R)
			};
			function K(T) {
				if (T < 0) {
					T = 0
				}
				if (T > 24) {
					T = 24
				}
				J = T;
				var S = T * (165 / 24);
				v.style.marginLeft = S + "px";
				var R = a.g("tcMove");
				if (a.browser.ie <= 6 && a.browser.ie > 0) {
					R.style.marginLeft = (S * 0.53) + "px"
				} else {
					R.style.marginLeft = S + "px"
				}
				M.hour = J;
				if (A) {
					M.showTraffic()
				} else {
					M.showTraffic({
								predictDate : {
									hour : M.hour,
									weekday : M.weekday
								}
							})
				}
				L()
			}
			function L() {
				var R = parseInt(v.style.marginLeft);
				var S = Math.ceil((R - 4) * 24 / 165);
				J = S;
				M.hour = S;
				if (S < 10) {
					S = "0" + S
				}
				if (A) {
					y.innerHTML = M.time;
					v.innerHTML = S + ":00"
				} else {
					y.innerHTML = v.innerHTML = S + ":00"
				}
			}
		}
	};
	g.prototype.showTraffic = function(j) {
		var k;
		if (this._trafficLayer) {
			this._map.removeTileLayer(this._trafficLayer)
		}
		if (j) {
			if (j.predictDate.weekday > 7 || j.predictDate.weekday < 1) {
				return
			}
			k = new BMap.TrafficLayer(j)
		} else {
			k = new BMap.TrafficLayer()
		}
		this.bShow = true;
		if (a.isMobile()) {
			a.dom.removeClass(this.btn, "maplibTcBtnOff" + h)
		}
		this._map.addTileLayer(k);
		this._trafficLayer = k
	};
	g.prototype.hideTraffic = function() {
		this.bShow = false;
		if (this._trafficLayer) {
			this._map.removeTileLayer(this._trafficLayer);
			this._trafficLayer = null
		}
		if (a.isMobile()) {
			a.dom.addClass(this.btn, "maplibTcBtnOff" + h)
		}
	};
	g.prototype.remove = function() {
		this.hideTraffic();
		var j = a.g("tcWrap");
		j.parentNode.removeChild(j);
		BMap.Control.prototype.remove.call(this);
		if (this.timer) {
			clearInterval(this.timer)
		}
	};
	function f(k, j, l) {
		if (!j || j.constructor != String) {
			return ""
		}
		j = {
			"for" : "htmlFor",
			"class" : "className"
		}[j] || j;
		if (typeof l != "undefined") {
			k[j] = l;
			if (k.setAttribute) {
				k.setAttribute(j, l)
			}
		}
		return k[j] || k.getAttribute(j) || ""
	}
	function d(l, k) {
		var n = document.createElement(l);
		k = k || {};
		for (var m in k) {
			value = k[m];
			m = j()[m] || m;
			if (m == "style") {
				n.style.cssText = value;
				continue
			}
			if (n.setAttribute) {
				n.setAttribute(m, value)
			} else {
				try {
					n[m] = value
				} catch (n) {
				}
			}
		}
		return n;
		function j() {
			var o = {
				cellpadding : "cellPadding",
				cellspacing : "cellSpacing",
				colspan : "colSpan",
				rowspan : "rowSpan",
				valign : "vAlign"
			};
			if (a.browser.ie < 8) {
				o["for"] = "htmlFor";
				o["class"] = "className"
			} else {
				o.htmlFor = "for";
				o.className = "class"
			}
			return o
		}
	}
	function i(j) {
		var k = {
			left : 0,
			top : 0
		};
		while (j && j.offsetParent) {
			k.left += j.offsetLeft;
			k.top += j.offsetTop;
			j = j.offsetParent
		}
		return k
	}
	function b(m, j, l) {
		var k, n;
		if (m.insertAdjacentHTML) {
			m.insertAdjacentHTML(j, l)
		} else {
			k = m.ownerDocument.createRange();
			j = j.toUpperCase();
			if (j == "AFTERBEGIN" || j == "BEFOREEND") {
				k.selectNodeContents(m);
				k.collapse(j == "AFTERBEGIN")
			} else {
				n = j == "BEFOREBEGIN";
				k[n ? "setStartBefore" : "setEndAfter"](m);
				k.collapse(n)
			}
			k.insertNode(k.createContextualFragment(l))
		}
		return m
	}
	function e(k, l) {
		var j = document.createElement("script");
		j.setAttribute("src", k);
		j.setAttribute("type", "text/javascript");
		j.setAttribute("charset", "gbk");
		if (j.addEventListener) {
			j.addEventListener("load", function(n) {
						var m = n.target;
						m.parentNode.removeChild(m);
						if (l) {
							l()
						}
					}, false)
		} else {
			if (j.attachEvent) {
				j.attachEvent("onreadystatechange", function(n) {
					var m = window.event.srcElement;
					if (m
							&& (m.readyState == "loaded" || m.readyState == "complete")) {
						m.parentNode.removeChild(m);
						if (l) {
							l()
						}
					}
				})
			}
		}
		setTimeout(function() {
					document.getElementsByTagName("head")[0].appendChild(j);
					j = null
				}, 1)
	}
	g.getTime = function(j) {
		this.curTime = isNaN(j) ? new Date() : new Date(j)
	}
})();

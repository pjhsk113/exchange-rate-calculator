package dev.application.infra.currencylayer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.application.domain.exchangerate.domain.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CurrencyLayerApiResponse {
    private boolean success;
    private long timestamp;
    private String terms;
    private String privacy;
    private String source;
    private Quotes quotes;

    @Getter
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    public static class Quotes {
        private BigDecimal usdzwl;
        private BigDecimal usdzmw;
        private BigDecimal usdzmk;
        private BigDecimal usdzar;
        private BigDecimal usdyer;
        private BigDecimal usdxpf;
        private BigDecimal usdxof;
        private BigDecimal usdxdr;
        private BigDecimal usdxcd;
        private BigDecimal usdxau;
        private BigDecimal usdxag;
        private BigDecimal usdxaf;
        private BigDecimal usdwst;
        private BigDecimal usdvuv;
        private BigDecimal usdvnd;
        private BigDecimal usdvef;
        private BigDecimal usduzs;
        private BigDecimal usduyu;
        private BigDecimal usdusd;
        private BigDecimal usdugx;
        private BigDecimal usduah;
        private BigDecimal usdtzs;
        private BigDecimal usdtwd;
        private BigDecimal usdttd;
        private BigDecimal usdtry;
        private BigDecimal usdtop;
        private BigDecimal usdtnd;
        private BigDecimal usdtmt;
        private BigDecimal usdtjs;
        private BigDecimal usdthb;
        private BigDecimal usdszl;
        private BigDecimal usdsyp;
        private BigDecimal usdsvc;
        private BigDecimal usdstd;
        private BigDecimal usdsrd;
        private BigDecimal usdsos;
        private BigDecimal usdsll;
        private BigDecimal usdshp;
        private BigDecimal usdsgd;
        private BigDecimal usdsek;
        private BigDecimal usdsdg;
        private BigDecimal usdscr;
        private BigDecimal usdsbd;
        private BigDecimal usdsar;
        private BigDecimal usdrwf;
        private BigDecimal usdrub;
        private BigDecimal usdrsd;
        private BigDecimal usdron;
        private BigDecimal usdqar;
        private BigDecimal usdpyg;
        private BigDecimal usdpln;
        private BigDecimal usdpkr;
        private BigDecimal usdphp;
        private BigDecimal usdpgk;
        private BigDecimal usdpen;
        private BigDecimal usdpab;
        private BigDecimal usdomr;
        private BigDecimal usdnzd;
        private BigDecimal usdnpr;
        private BigDecimal usdnok;
        private BigDecimal usdnio;
        private BigDecimal usdngn;
        private BigDecimal usdnad;
        private BigDecimal usdmzn;
        private BigDecimal usdmyr;
        private BigDecimal usdmxn;
        private BigDecimal usdmwk;
        private BigDecimal usdmvr;
        private BigDecimal usdmur;
        private BigDecimal usdmro;
        private BigDecimal usdmop;
        private BigDecimal usdmnt;
        private BigDecimal usdmmk;
        private BigDecimal usdmkd;
        private BigDecimal usdmga;
        private BigDecimal usdmdl;
        private BigDecimal usdmad;
        private BigDecimal usdlyd;
        private BigDecimal usdlvl;
        private BigDecimal usdltl;
        private BigDecimal usdlsl;
        private BigDecimal usdlrd;
        private BigDecimal usdlkr;
        private BigDecimal usdlbp;
        private BigDecimal usdlak;
        private BigDecimal usdkzt;
        private BigDecimal usdkyd;
        private BigDecimal usdkwd;
        private BigDecimal usdkrw;
        private BigDecimal usdkpw;
        private BigDecimal usdkmf;
        private BigDecimal usdkhr;
        private BigDecimal usdkgs;
        private BigDecimal usdkes;
        private BigDecimal usdjpy;
        private BigDecimal usdjod;
        private BigDecimal usdjmd;
        private BigDecimal usdjep;
        private BigDecimal usdisk;
        private BigDecimal usdirr;
        private BigDecimal usdiqd;
        private BigDecimal usdinr;
        private BigDecimal usdimp;
        private BigDecimal usdils;
        private BigDecimal usdidr;
        private BigDecimal usdhuf;
        private BigDecimal usdhtg;
        private BigDecimal usdhrk;
        private BigDecimal usdhnl;
        private BigDecimal usdhkd;
        private BigDecimal usdgyd;
        private BigDecimal usdgtq;
        private BigDecimal usdgnf;
        private BigDecimal usdgmd;
        private BigDecimal usdgip;
        private BigDecimal usdghs;
        private BigDecimal usdggp;
        private BigDecimal usdgel;
        private BigDecimal usdgbp;
        private BigDecimal usdfkp;
        private BigDecimal usdfjd;
        private BigDecimal usdeur;
        private BigDecimal usdetb;
        private BigDecimal usdern;
        private BigDecimal usdegp;
        private BigDecimal usddzd;
        private BigDecimal usddop;
        private BigDecimal usddkk;
        private BigDecimal usddjf;
        private BigDecimal usdczk;
        private BigDecimal usdcve;
        private BigDecimal usdcup;
        private BigDecimal usdcuc;
        private BigDecimal usdcrc;
        private BigDecimal usdcop;
        private BigDecimal usdcny;
        private BigDecimal usdclp;
        private BigDecimal usdclf;
        private BigDecimal usdchf;
        private BigDecimal usdcdf;
        private BigDecimal usdcad;
        private BigDecimal usdbzd;
        private BigDecimal usdbyr;
        private BigDecimal usdbyn;
        private BigDecimal usdbwp;
        private BigDecimal usdbtn;
        private BigDecimal usdbtc;
        private BigDecimal usdbsd;
        private BigDecimal usdbrl;
        private BigDecimal usdbob;
        private BigDecimal usdbnd;
        private BigDecimal usdbmd;
        private BigDecimal usdbif;
        private BigDecimal usdbhd;
        private BigDecimal usdbgn;
        private BigDecimal usdbdt;
        private BigDecimal usdbbd;
        private BigDecimal usdbam;
        private BigDecimal usdazn;
        private BigDecimal usdawg;
        private BigDecimal usdaud;
        private BigDecimal usdars;
        private BigDecimal usdaoa;
        private BigDecimal usdang;
        private BigDecimal usdamd;
        private BigDecimal usdall;
        private BigDecimal usdafn;
        private BigDecimal usdaed;
    }

    public static ExchangeRate newExchangeRate(CurrencyLayerApiResponse response) {
        return new ExchangeRate(
                response.getQuotes().getUsdkrw(),
                response.getQuotes().getUsdjpy(),
                response.getQuotes().getUsdphp()
        );
    }
}


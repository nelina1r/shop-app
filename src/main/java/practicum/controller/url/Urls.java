package practicum.controller.url;

public interface Urls {
    String ROOT = "/api";

    interface Auth{
        String NAME = "auth";
        String FULL = ROOT + "/" + NAME;

        interface Login{
            String NAME = "login";
            String FULL = Auth.FULL + "/" + NAME;
        }
        interface Register{
            String NAME = "register";
            String FULL = Auth.FULL + "/" + NAME;
        }
    }

    interface User{
        String NAME = "user";
        String FULL = ROOT + "/" + NAME;

        interface Id{
            String NAME = "{id}";
            String FULL = User.FULL + "/" + NAME;
        }
    }

    interface Item {
        String NAME = "item";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Item.FULL + "/" + NAME;
        }

        interface Buy {
            String NAME = "buy";
            String FULL = Item.FULL + "/" + NAME;

            interface Id {
                String NAME = "{id}";
                String FULL = Buy.FULL + "/" + NAME;
            }
        }

        interface Debite {
            String NAME = "debite";
            String FULL = Item.FULL + "/" + NAME;
        }
    }

    interface Shop {
        String NAME = "shop";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Shop.FULL + "/" + NAME;
        }
    }

    interface Waybill {
        String NAME = "waybill";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Waybill.FULL + "/" + NAME;
        }

        interface Clearlist {
            String NAME = "clearlist";
            String FULL = Waybill.FULL + "/" + NAME;
        }

        interface Buy {
            String NAME = "buy";
            String FULL = Waybill.FULL + "/" + NAME;
        }
    }
}

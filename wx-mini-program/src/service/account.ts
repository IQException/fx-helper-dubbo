import api from "@/api/backend";
import { useHeadStore } from "@/store/head";
import utils from "@/utils";

class Account {
  private static instance: Account;

  private headStore = useHeadStore();

  private constructor() {}

  static getInstance() {
    if (!Account.instance) {
      Account.instance = new Account();
    }
    return Account.instance;
  }

  async getAccount() {
    const ret = await api.getAccount({
      head: this.headStore.head,
    });
    if (utils.success(ret.data)) {
      return ret.data.result!;
    }
  }
}

export default Account.getInstance();

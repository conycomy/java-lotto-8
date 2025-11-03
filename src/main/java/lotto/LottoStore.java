package lotto;

import static lotto.LottoNumberRange.PRICE_TICKET;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    private final List<Lotto> purchasedLottos;
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoStore(int purchaseAmount, LottoNumberGenerator generator) {
        this.lottoNumberGenerator = generator;
        this.purchasedLottos = createLottos(purchaseAmount);
    }

    private List<Lotto> createLottos(int purchaseAmount) {
        int count = purchaseAmount / PRICE_TICKET;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> numbers = this.lottoNumberGenerator.generate();
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }
}
package com.malouane.fenkolo.domain.common;

import io.reactivex.Observable;

// A base class use cases to be executed on Presentation

public abstract class UseCase<In, Out> {
  private Schedulers schedulers;

  public UseCase(Schedulers schedulers) {
    this.schedulers = schedulers;
  }

  public abstract Observable<Out> buildObservable(In input);

  public Observable<Out> perform(In input) {
    // https://medium.com/yammer-engineering/chaining-multiple-sources-with-rxjava-20eb6850e5d9
    return buildObservable(input).subscribeOn(schedulers.subscribeOn())
        .observeOn(schedulers.observeOn(), true);
  }
}

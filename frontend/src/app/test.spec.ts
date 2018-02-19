describe('tests on headless chrome', () => {
  it('should be executed on headless chrome', () => {
    const result = window.navigator.userAgent;
    expect(result.includes('Chrome')).toBe(true);
  });
});
